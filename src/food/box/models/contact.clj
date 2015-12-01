(ns food.box.models.contact
  (:require [bouncer.validators :refer [required email]]))

(def validator
  {:name    [[required :message "Name can't be blank"]]
   :email   [[required :message "Email Address can't be blank"]
             [email    :message "Email Address must be a valid address"]]
   :message [[required :message "Message can't be blank"]]})
