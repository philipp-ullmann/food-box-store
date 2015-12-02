(ns food.box.controllers.contact
  (:require [taoensso.timbre        :as log]
            [food.box.views.contact :as view]
            [bouncer.core           :refer [valid?]]
            [compojure.core         :refer [defroutes GET POST]]

            [food.box.models [utils   :refer [assoc-errors now]]
                             [contact :refer [validator]]
                             [conf    :refer [EMAIL-ENABLED?]]
                             [mailer  :refer [send-contact-message!]]]))

(defn create!
  "Validates a contact message and forwards the message via email."
  [contact]

  ; VALIDATION
  (if (valid? contact validator)

    ; SUCCESS
    (let [contact (assoc contact :created-at (now))]

      (log/info "Message received:" (select-keys contact [:name :email]))

      (when EMAIL-ENABLED?
        (send-contact-message! contact))

      (view/created))

    ; FAILED
    (view/show (assoc-errors contact validator))))

(defroutes routes
  (GET  "/contact" []        (view/show))
  (POST "/contact" [contact] (create! contact)))
