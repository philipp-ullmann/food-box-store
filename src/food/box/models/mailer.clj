(ns food.box.models.mailer
  (:require [postal.core           :refer [send-message]]
            [food.box.models.conf  :refer [GMAIL-CREDENTIALS]]
            [food.box.views.mailer :as view]))

(def con
  {:host "smtp.gmail.com"
   :user (:email    GMAIL-CREDENTIALS)
   :pass (:password GMAIL-CREDENTIALS)
   :ssl  true})

(defn send-order-confirmation!
  "Sends an order confirmation email to the client."
  [order]
  (send-message
    con
    {:from    (:email GMAIL-CREDENTIALS)
     :to      (:email order)
     :subject "Alps food box order confirmation"

     :body [{:type    "text/html; charset=utf-8"
             :content (view/confirmation order)}]}))

(defn send-order-notification!
  "Sends an order notification email."
  [order]
  (send-message
    con
    {:from    (:email GMAIL-CREDENTIALS)
     :to      (:email GMAIL-CREDENTIALS)
     :subject (str "Alps food box order: " (:number order))

     :body [{:type    "text/html; charset=utf-8"
             :content (view/notification order)}]}))

(defn send-exception-notification!
  "Sends an order notification email."
  [exception]
  (send-message
    con
    {:from    (:email GMAIL-CREDENTIALS)
     :to      (:email GMAIL-CREDENTIALS)
     :subject (str "Alps food box exception: " exception)

     :body [{:type    "text/html; charset=utf-8"
             :content (view/exception exception)}]}))

(defn send-contact-message!
  "Sends a contact message email."
  [contact]
  (send-message
    con
    {:from    (:email contact)
     :to      (:email GMAIL-CREDENTIALS)
     :subject (str "Alps food box message: " (:name contact))
     :body    (:message contact)}))
