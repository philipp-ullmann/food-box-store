(ns food.box.models.order
  (:require [bouncer.validators      :refer [member required email]]
            [food.box.models.conf    :refer [BOXES]]
            [food.box.models.country :refer [COUNTRIES]]
            
            (clj-time [core   :as t]
                      [format :as tf])))

(def validator
  {:box             [[member BOXES :message "Unknown food box size"]]
   :first-name      [[required :message "First Name can't be blank"]]
   :last-name       [[required :message "Last Name can't be blank"]]
   :email           [[required :message "Email Address can't be blank"]
                     [email    :message "Email Address must be a valid address"]]
   :street          [[required :message "Street can't be blank"]]
   :postcode        [[required :message "Postcode / Zip can't be blank"]]
   :city            [[required :message "Town / City can't be blank"]]
   :country         [[required :message "Country can't be blank"]
                     [member COUNTRIES :message "Orders for the given country are not accepted"]]
   :terms-accepted  [[required :message "General business terms must be accepted"]]})

(defn now
  "Returns the current time for the timezone Europe/Vienna."
  []
  (tf/unparse (tf/with-zone (tf/formatter "yyyy-MM-dd HH:mm:ss")
                            (t/time-zone-for-id "Europe/Vienna"))
              (t/now)))
