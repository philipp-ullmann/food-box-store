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
      (fill-in "First Name *"     "Philipp")
      (fill-in "Last Name *"      "Ullmann")
      (fill-in "Email Address *"  "ullmann.philipp@gmail.com")
      (fill-in "Street *"         "Boldrinigasse 1/6")
      (fill-in "Postcode / Zip *" "2500")
      (fill-in "Town / City *"    "Baden")
      (choose  "Country *"        "Austria")
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

      ; Validation errors
      (has (some-text? "First Name can't be blank"))
      (has (some-text? "Last Name can't be blank"))
      (has (some-text? "Email Address can't be blank"))
      (has (some-text? "Street can't be blank"))
      (has (some-text? "Postcode / Zip can't be blank"))
      (has (some-text? "Town / City can't be blank"))
      (has (some-text? "General business terms must be accepted"))))

(deftest user-cannot-order-a-regular-box-with-an-invalid-email-address
  (-> (session app)
      (visit "/")

      ; Choose regular box
      (within [:#regular-box]
        (press "Choose"))

      ; Fill in all fields of the order formular
      (has (some-text? (str "Order \"" order/REGULAR "\" box")))
      (fill-in "First Name *"     "Philipp")
      (fill-in "Last Name *"      "Ullmann")
      (fill-in "Email Address *"  "ullmann.philipp@gmail")
      (fill-in "Street *"         "Boldrinigasse 1/6")
      (fill-in "Postcode / Zip *" "2500")
      (fill-in "Town / City *"    "Baden")
      (choose  "Country *"        "Austria")
      (check   "I've read the terms and conditions")
      (press   "Confirm")

      ; Validation errors
      (has (some-text? "Email Address must be a valid address"))))

(deftest user-cannot-order-an-unknown-box
  (-> (session app)
      (visit "/order?box=unknown")

      ; Fill in all fields of the order formular
      (has (some-text? "Order \"unknown\" box"))
      (fill-in "First Name *"     "Philipp")
      (fill-in "Last Name *"      "Ullmann")
      (fill-in "Email Address *"  "ullmann.philipp@gmail.com")
      (fill-in "Street *"         "Boldrinigasse 1/6")
      (fill-in "Postcode / Zip *" "2500")
      (fill-in "Town / City *"    "Baden")
      (choose  "Country *"        "Austria")
      (check   "I've read the terms and conditions")
      (press   "Confirm")

      ; Validation errors
      (has (some-text? "Unknown food box size"))))
