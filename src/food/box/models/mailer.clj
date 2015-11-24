(ns food.box.models.mailer
  (:require [environ.core          :refer [env]]
            [postal.core           :refer [send-message]]
            [food.box.views.mailer :as view]))

(def con
  {:host "smtp.gmail.com"
   :user (:mailer-email env)
   :pass (:mailer-password env)
   :ssl  true})

(defn send-order-confirmation!
  "Sends an order confirmation email to the client."
  [order]
  (send-message
    con
    {:from    (:mailer-email env)
     :to      (:email order)
     :subject "Alps food box order confirmation"

     :body [{:type    "text/html; charset=utf-8"
             :content (view/confirmation order)}]}))
