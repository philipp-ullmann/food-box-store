(ns food.box.controllers.contact
  (:require [food.box.views.contact :as view]
            [bouncer.core           :as b]
            [compojure.core         :refer [defroutes GET POST]]

            [food.box.models [contact :refer [validator]]
                             [conf    :refer [EMAIL-ENABLED?]]
                             [mailer  :refer [send-contact-message!]]]))

(defn create
  "Validates a contact message and forwards the message via email."
  [contact]

  ; VALIDATION
  (if (b/valid? contact validator)

    ; SUCCESS
    (when EMAIL-ENABLED?
      (send-contact-message! contact))

    (view/create contact)

    ; FAILED
    (->> (b/validate contact validator)
         second
         ::b/errors
         (assoc contact :errors)
         view/show)))

(defroutes routes
  (GET  "/contact" []        (view/show))
  (POST "/contact" [contact] (create contact)))
