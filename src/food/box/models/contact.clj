(ns food.box.models.contact
  (:require [bouncer.validators :refer [required email]]))

(def validator
  {:name    [[required :message "can't be blank"]]
   :email   [[required :message "can't be blank"]
             [email    :message "must be a valid address"]]
   :message [[required :message "can't be blank"]]})
