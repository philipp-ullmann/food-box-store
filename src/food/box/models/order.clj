(ns food.box.models.order
  (:require [bouncer.validators      :refer [member required email]]
            [food.box.models.conf    :refer [BOXES]]
            [food.box.models.country :refer [COUNTRIES]]))

(def validator
  {:box             [[member BOXES :message "Unknown food box size"]]
   :first-name      [[required :message "can't be blank"]]
   :last-name       [[required :message "can't be blank"]]
   :email           [[required :message "can't be blank"]
                     [email    :message "must be a valid address"]]
   :street          [[required :message "can't be blank"]]
   :postcode        [[required :message "can't be blank"]]
   :city            [[required :message "can't be blank"]]
   :country         [[required :message "can't be blank"]
                     [member (keys COUNTRIES) :message "unknown country"]]
   :terms-accepted  [[required :message "must be accepted"]]})
