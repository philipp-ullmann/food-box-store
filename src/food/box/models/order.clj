(ns food.box.models.order
  (:require [bouncer.validators      :as v]
            [food.box.models.country :refer [COUNTRIES]])) 

; BOXES
(def SMALL   "small")
(def REGULAR "regular")
(def PREMIUM "premium")

(def BOXES [SMALL REGULAR PREMIUM])

(def validator
  {:box             [[v/member BOXES :message "Unknown food box size"]]
   :first-name      [[v/required :message "First Name can't be blank"]]
   :last-name       [[v/required :message "Last Name can't be blank"]]
   :email           [[v/required :message "Email Address can't be blank"]
                     [v/email    :message "Email Address must be a valid address"]]
   :street          [[v/required :message "Street can't be blank"]]
   :postcode        [[v/required :message "Postcode / Zip can't be blank"]]
   :city            [[v/required :message "Town / City can't be blank"]]
   :country         [[v/required :message "Country can't be blank"]
                     [v/member COUNTRIES :message "Orders for the given country are not accepted"]]
   :terms-accepted  [[v/required :message "General business terms must be accepted"]]})
