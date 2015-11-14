(ns food.box.store-test
  (:require [kerodon.core          :refer :all]
            [kerodon.test          :refer :all]
            [clojure.test          :refer :all]
            [environ.core          :refer [env]]
            [food.box.store        :refer [app]]
            [food.box.models.order :as order]))

(deftest user-can-see-prices-of-boxes
  (-> (session app)
      (visit "/")
      (has (some-text? (:price-small env)))
      (has (some-text? (:price-regular env)))
      (has (some-text? (:price-premium env)))))

(deftest user-can-choose-a-box-to-order
  (-> (session app)
      (visit "/")

      ; Choose small box
      (within [:#small-box]
        (press "Choose"))
      (has (some-text? (str "Order \"" order/SMALL "\" box")))

      ; Choose regular box
      (visit "/")
      (within [:#regular-box]
        (press "Choose"))
      (has (some-text? (str "Order \"" order/REGULAR "\" box")))

      ; Choose premium box
      (visit "/")
      (within [:#premium-box]
        (press "Choose"))
      (has (some-text? (str "Order \"" order/PREMIUM "\" box")))))

(deftest user-can-order-a-small-box
  (-> (session app)
      (visit "/")

      ; Choose small box
      (within [:#small-box]
        (press "Choose"))

      ; Fill in all fields of the order formular
      (has (some-text? (str "Order \"" order/SMALL "\" box")))
      (fill-in "First Name*" "Philipp")
      (fill-in "Last Name*"  "Ullmann")
      (fill-in "E-Mail*"     "ullmann.philipp@gmail.com")
      (fill-in "Street*"     "Boldrinigasse 1/6")
      (fill-in "City*"       "Baden")
      (fill-in "State*"      "Niederösterreich")
      (choose  "Country*"    "Austria")
      (check   "I've read the terms and conditions")
      (press   "Confirm")

      ; Start page
      (follow-redirect)
      (has (some-text? "How it works")))) 

(deftest user-cannot-order-a-regular-box-with-missing-order-fields
  (-> (session app)
      (visit "/")

      ; Choose regular box
      (within [:#regular-box]
        (press "Choose"))

      ; Do not fill in a field of the order formular
      (has (some-text? (str "Order \"" order/REGULAR "\" box")))
      (press "Confirm")

      ; Order page
      (has (some-text? (str "Order \"" order/REGULAR "\" box"))))) 
