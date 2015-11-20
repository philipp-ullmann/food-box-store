(ns food.box.store-test
  (:require [kerodon.core          :refer :all]
            [kerodon.test          :refer :all]
            [clojure.test          :refer :all]
            [food.box.matcher      :refer :all]
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

      ; CHOOSE SMALL
      (within [:#small-box]
        (press "Choose"))
      (has (some-text? (str "Order \"" order/SMALL "\" box")))

      ; CHOOSE REGULAR
      (visit "/")
      (within [:#regular-box]
        (press "Choose"))
      (has (some-text? (str "Order \"" order/REGULAR "\" box")))

      ; CHOOSE PREMIUM
      (visit "/")
      (within [:#premium-box]
        (press "Choose"))
      (has (some-text? (str "Order \"" order/PREMIUM "\" box")))))

(deftest user-can-order-a-small-box
  (-> (session app)
      (visit "/")

      ; CHOOSE BOX
      (within [:#small-box]
        (press "Choose"))

      ; FILL IN
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

      ; PRICING PAGE
      (follow-redirect)
      (has (some-text? "How it works"))
      (has (some-text? "Thank you for your order! Order number: ")))) 

(deftest user-cannot-order-a-regular-box-with-missing-order-fields
  (-> (session app)
      (visit "/")

      ; CHOOSE BOX
      (within [:#regular-box]
        (press "Choose"))

      ; FILL IN
      (has (some-text? (str "Order \"" order/REGULAR "\" box")))
      (press "Confirm")

      ; VALIDATION ERRORS
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

      ; CHOOSE BOX
      (within [:#regular-box]
        (press "Choose"))

      ; FILL IN
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

      ; VALIDATION ERRORS
      (has (some-text? "Email Address must be a valid address"))))

(deftest user-cannot-order-an-unknown-box
  (-> (session app)
      (visit "/order?box=unknown")

      ; FILL IN
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

      ; FIELD VALUES
      (has (value?    "First Name *"     "Philipp"))
      (has (value?    "Last Name *"      "Ullmann"))
      (has (value?    "Email Address *"  "ullmann.philipp@gmail.com"))
      (has (value?    "Street *"         "Boldrinigasse 1/6"))
      (has (value?    "Postcode / Zip *" "2500"))
      (has (value?    "Town / City *"    "Baden"))
      ;(has (selected? "Country *",       "Austria"))
      (has (checked?  "I've read the terms and conditions"))

      ; VALIDATION ERRORS
      (has (some-text? "Unknown food box size"))))
