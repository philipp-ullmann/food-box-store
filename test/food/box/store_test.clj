(ns food.box.store-test
  (:require [kerodon.core          :refer :all]
            [kerodon.test          :refer :all]
            [clojure.test          :refer :all]
            [food.box.matcher      :refer :all]
            [food.box.store        :refer [app]]
            [food.box.models.conf  :refer [PRICES SMALL REGULAR PREMIUM
                                           BANK-ACCOUNT]]))

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

        (has (some-text? (str "Order a \"" box-name "\" box")))

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
        (fill-in "Email Address *"  "philipp.ullmann@gmail.com")
        (fill-in "Street *"         "Boldrinigasse 1/6")
        (fill-in "Postcode / Zip *" "2500")
        (fill-in "Town / City *"    "Baden")
        (choose  "Country *"        "Austria")
        (check   "I've read the terms and conditions")
        (press   "Submit")

        ; ORDER CONFIRMATION PAGE
        (has (some-text? (str "Thank you for ordering a " box-name " box.")))
        (has (some-text? (get PRICES box-name)))
        (has (some-text? (:bank BANK-ACCOUNT)))
        (has (some-text? (:country BANK-ACCOUNT)))
        (has (some-text? (:owner BANK-ACCOUNT)))
        (has (some-text? (:bic BANK-ACCOUNT)))
        (has (some-text? (:iban BANK-ACCOUNT)))
        (has (some-text? "Philipp Ullmann"))
        (has (some-text? "Boldrinigasse 1/6"))
        (has (some-text? "2500 Baden"))
        (has (some-text? "Austria"))
        (has (some-text? "philipp.ullmann@gmail.com"))
        (follow "<< Main page")

        ; PRICING PAGE
        (has (some-text? "HOW IT WORKS")))))

;; ABORT ORDER
;; ============================================================================

(deftest abort-box-order
  (doseq [[box-id box-name] BOXES]

    (-> (session app)
        (visit "/")

        ; CHOOSE BOX
        (within [box-id]
          (press "Choose"))

        (has (some-text? (str "Order a \"" box-name "\" box")))

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

        (follow "Cancel")

        ; PRICING PAGE
        (has (some-text? "HOW IT WORKS")))))

;; EMPTY ORDER FIELDS
;; ============================================================================

(deftest failing-box-order-due-to-missing-order-fields
  (doseq [[box-id box-name] BOXES]

    (-> (session app)
        (visit "/")

        ; CHOOSE BOX
        (within [box-id]
          (press "Choose"))

        (has (some-text? (str "Order a \"" box-name "\" box")))

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
        (press "Submit")

        ; VALIDATION ERRORS
        (has (some-text? "can't be blank"))
        (has (some-text? "must be accepted"))

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

        (has (some-text? (str "Order a \"" box-name "\" box")))

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
        (fill-in "Email Address *"  "philipp.ullmann@gmail")
        (fill-in "Street *"         "Boldrinigasse 1/6")
        (fill-in "Postcode / Zip *" "2500")
        (fill-in "Town / City *"    "Baden")
        (choose  "Country *"        "Austria")
        (check   "I've read the terms and conditions")
        (press   "Submit")

        ; VALIDATION ERRORS
        (has (some-text? "must be a valid address"))

        ; FIELD VALUES
        (has (value?    [:input#box]       box-name))
        (has (value?    "First Name *"     "Philipp"))
        (has (value?    "Last Name *"      "Ullmann"))
        (has (value?    "Email Address *"  "philipp.ullmann@gmail"))
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

      (has (some-text? "Order a \"unknown\" box"))

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
      (fill-in "Email Address *"  "philipp.ullmann@gmail.com")
      (fill-in "Street *"         "Boldrinigasse 1/6")
      (fill-in "Postcode / Zip *" "2500")
      (fill-in "Town / City *"    "Baden")
      (choose  "Country *"        "Austria")
      (check   "I've read the terms and conditions")
      (press   "Submit")

      ; VALIDATION ERRORS
      (has (some-text? "Unknown food box size"))

      ; FIELD VALUES
      (has (value?    [:input#box]       "unknown"))
      (has (value?    "First Name *"     "Philipp"))
      (has (value?    "Last Name *"      "Ullmann"))
      (has (value?    "Email Address *"  "philipp.ullmann@gmail.com"))
      (has (value?    "Street *"         "Boldrinigasse 1/6"))
      (has (value?    "Postcode / Zip *" "2500"))
      (has (value?    "Town / City *"    "Baden"))
      (has (selected? "Country *",       "Austria"))
      (has (checked?  "I've read the terms and conditions"))))

;; SUCCESSFUL CONTACT
;; ============================================================================

(deftest successful-contact-message
  (-> (session app)
      (visit "/contact")

      (has (some-text? "Contact"))

      ; FIELD VALUES
      (has (value?   "Name *"          ""))
      (has (value?   "Email Address *" ""))
      (has (content? "Message *"       ""))

      ; FILL IN
      (fill-in "Name *"          "Philipp Ullmann")
      (fill-in "Email Address *" "philipp.ullmann@gmail.com")
      (fill-in "Message *"       "I have some questions")
      (press   "Send")

      ; ORDER CONFIRMATION PAGE
      (has (some-text? "Thank you for your message!"))
      (follow "<< Main page")

      ; PRICING PAGE
      (has (some-text? "HOW IT WORKS"))))

;; EMPTY CONTACT FIELDS
;; ============================================================================

(deftest failing-contact-message-due-to-missing-fields
  (-> (session app)
      (visit "/contact")

      (has (some-text? "Contact"))

      ; FIELD VALUES
      (has (value?   "Name *"          ""))
      (has (value?   "Email Address *" ""))
      (has (content? "Message *"       ""))

      ; FILL IN
      (press "Send")

      ; ORDER CONFIRMATION PAGE
      (has (some-text? "Contact"))

      ; VALIDATION ERRORS
      (has (some-text? "can't be blank"))

      ; FIELD VALUES
      (has (value?   "Name *"          ""))
      (has (value?   "Email Address *" ""))
      (has (content? "Message *"       ""))))

;; INVALID EMAIL ADDRESS
;; ============================================================================

(deftest failing-contact-message-due-to-invalid-email-address
  (-> (session app)
      (visit "/contact")

      (has (some-text? "Contact"))

      ; FIELD VALUES
      (has (value?   "Name *"          ""))
      (has (value?   "Email Address *" ""))
      (has (content? "Message *"       ""))

      ; FILL IN
      (fill-in "Name *"          "Philipp Ullmann")
      (fill-in "Email Address *" "philipp.ullmanngmail.com")
      (fill-in "Message *"       "I have some questions")
      (press "Send")

      ; ORDER CONFIRMATION PAGE
      (has (some-text? "Contact"))

      ; VALIDATION ERRORS
      (has (some-text? "must be a valid address"))

      ; FIELD VALUES
      (has (value?   "Name *"          "Philipp Ullmann"))
      (has (value?   "Email Address *" "philipp.ullmanngmail.com"))
      (has (content? "Message *"       "I have some questions"))))
