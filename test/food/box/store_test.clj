(ns food.box.store-test
  (:require [kerodon.core          :refer :all]
            [kerodon.test          :refer :all]
            [clojure.test          :refer :all]
            [food.box.matcher      :refer :all]
            [food.box.store        :refer [app]]
            [food.box.models.conf  :refer [PRICES SMALL REGULAR PREMIUM]]))

(def BOXES [[:#small-box   SMALL]
            [:#regular-box REGULAR]
            [:#premium-box PREMIUM]])

;; PRICING
;; ============================================================================

(deftest box-prices
  (-> (session app)
      (visit "/")
      (has (some-text? (get PRICES SMALL)))
      (has (some-text? (get PRICES REGULAR)))
      (has (some-text? (get PRICES PREMIUM)))))

;; SUCCESSFUL ORDER
;; ============================================================================

(deftest successful-box-order
  (doseq [[box-id box-name] BOXES]

    (-> (session app)
        (visit "/")

        ; CHOOSE BOX
        (within [box-id]
          (press "Choose"))

        (has (some-text? (str "Order \"" box-name "\" box")))

        ; FIELD VALUES
        (has (value?         [:input#box]       box-name))
        (has (value?         "First Name *"     ""))
        (has (value?         "Last Name *"      ""))
        (has (value?         "Email Address *"  ""))
        (has (value?         "Street *"         ""))
        (has (value?         "Postcode / Zip *" ""))
        (has (value?         "Town / City *"    ""))
        (has (no-selections? "Country *"))
        (has (unchecked?     "I've read the terms and conditions"))

        ; FILL IN
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
        (has (some-text? "Thank you for your order! Order number: ")))))

;; EMPTY ORDER FIELDS
;; ============================================================================

(deftest failing-box-order-due-to-missing-order-fields
  (doseq [[box-id box-name] BOXES]

    (-> (session app)
        (visit "/")

        ; CHOOSE BOX
        (within [box-id]
          (press "Choose"))

        (has (some-text? (str "Order \"" box-name "\" box")))

        ; FIELD VALUES
        (has (value?         [:input#box]       box-name))
        (has (value?         "First Name *"     ""))
        (has (value?         "Last Name *"      ""))
        (has (value?         "Email Address *"  ""))
        (has (value?         "Street *"         ""))
        (has (value?         "Postcode / Zip *" ""))
        (has (value?         "Town / City *"    ""))
        (has (no-selections? "Country *"))
        (has (unchecked?     "I've read the terms and conditions"))
        (press "Confirm")

        ; VALIDATION ERRORS
        (has (some-text? "First Name can't be blank"))
        (has (some-text? "Last Name can't be blank"))
        (has (some-text? "Email Address can't be blank"))
        (has (some-text? "Street can't be blank"))
        (has (some-text? "Postcode / Zip can't be blank"))
        (has (some-text? "Town / City can't be blank"))
        (has (some-text? "General business terms must be accepted"))

        ; FIELD VALUES
        (has (value?     [:input#box]       box-name))
        (has (value?     "First Name *"     ""))
        (has (value?     "Last Name *"      ""))
        (has (value?     "Email Address *"  ""))
        (has (value?     "Street *"         ""))
        (has (value?     "Postcode / Zip *" ""))
        (has (value?     "Town / City *"    ""))
        (has (selected?  "Country *" "Afghanistan"))
        (has (unchecked? "I've read the terms and conditions")))))

;; INVALID EMAIL ADDRESS
;; ============================================================================

(deftest failing-box-order-due-to-invalid-email-address
  (doseq [[box-id box-name] BOXES]

    (-> (session app)
        (visit "/")

        ; CHOOSE BOX
        (within [box-id]
          (press "Choose"))

        (has (some-text? (str "Order \"" box-name "\" box")))

        ; FIELD VALUES
        (has (value?         [:input#box]       box-name))
        (has (value?         "First Name *"     ""))
        (has (value?         "Last Name *"      ""))
        (has (value?         "Email Address *"  ""))
        (has (value?         "Street *"         ""))
        (has (value?         "Postcode / Zip *" ""))
        (has (value?         "Town / City *"    ""))
        (has (no-selections? "Country *"))
        (has (unchecked?     "I've read the terms and conditions"))

        ; FILL IN
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
        (has (some-text? "Email Address must be a valid address"))

        ; FIELD VALUES
        (has (value?    [:input#box]       box-name))
        (has (value?    "First Name *"     "Philipp"))
        (has (value?    "Last Name *"      "Ullmann"))
        (has (value?    "Email Address *"  "ullmann.philipp@gmail"))
        (has (value?    "Street *"         "Boldrinigasse 1/6"))
        (has (value?    "Postcode / Zip *" "2500"))
        (has (value?    "Town / City *"    "Baden"))
        (has (selected? "Country *"        "Austria"))
        (has (checked?  "I've read the terms and conditions")))))

;; INVALID BOX
;; ============================================================================

(deftest failing-box-order-due-to-invalid-box
  (-> (session app)
      (visit "/order?box=unknown")

      (has (some-text? "Order \"unknown\" box"))

      ; FIELD VALUES
      (has (value?         [:input#box]       "unknown"))
      (has (value?         "First Name *"     ""))
      (has (value?         "Last Name *"      ""))
      (has (value?         "Email Address *"  ""))
      (has (value?         "Street *"         ""))
      (has (value?         "Postcode / Zip *" ""))
      (has (value?         "Town / City *"    ""))
      (has (no-selections? "Country *"))
      (has (unchecked?     "I've read the terms and conditions"))

      ; FILL IN
      (fill-in "First Name *"     "Philipp")
      (fill-in "Last Name *"      "Ullmann")
      (fill-in "Email Address *"  "ullmann.philipp@gmail.com")
      (fill-in "Street *"         "Boldrinigasse 1/6")
      (fill-in "Postcode / Zip *" "2500")
      (fill-in "Town / City *"    "Baden")
      (choose  "Country *"        "Austria")
      (check   "I've read the terms and conditions")
      (press   "Confirm")

      ; VALIDATION ERRORS
      (has (some-text? "Unknown food box size"))

      ; FIELD VALUES
      (has (value?    [:input#box]       "unknown"))
      (has (value?    "First Name *"     "Philipp"))
      (has (value?    "Last Name *"      "Ullmann"))
      (has (value?    "Email Address *"  "ullmann.philipp@gmail.com"))
      (has (value?    "Street *"         "Boldrinigasse 1/6"))
      (has (value?    "Postcode / Zip *" "2500"))
      (has (value?    "Town / City *"    "Baden"))
      (has (selected? "Country *",       "Austria"))
      (has (checked?  "I've read the terms and conditions"))))
