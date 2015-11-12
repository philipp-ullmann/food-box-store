(ns food.box.models.order
  (:require [bouncer.validators      :as v]
            [food.box.models.country :refer [countries]])) 

; BOXES
(def SMALL   "small")
(def REGULAR "regular")
(def PREMIUM "premium")

(def validator
  {:first-name      [[v/required :message "First name can't be blank"]]
   :last-name       [[v/required :message "Last name can't be blank"]]
   :email           [[v/required :message "Email address can't be blank"]
                     [v/email    :message "Email must be a valid address"]]
   :city            [[v/required :message "City can't be blank"]]
   :state           [[v/required :message "State can't be blank"]]
   :country         [[v/required :message "Country can't be blank"]
                     [v/member countries :message "Orders for the given country are not accepted"]]
   :terms-accepted? [[true? :message "General business terms must be accepted"]]})
