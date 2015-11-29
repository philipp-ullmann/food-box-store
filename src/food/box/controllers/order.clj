(ns food.box.controllers.order
  (:require [taoensso.timbre      :as log]
            [food.box.views.order :as view]
            [bouncer.core         :as b]
            [crypto.random        :refer [base32]]
            [ring.util.response   :refer [redirect]]
            [compojure.core       :refer [defroutes GET POST]]

            [food.box.models [order  :refer [validator now]]
                             [conf   :refer [PRICES EMAIL-ENABLED?]]
                             [mailer :refer [send-order-confirmation!
                                             send-order-notification!]]]))

(defn create
  "Validates an order and sends confirmation and notification emails."
  [order]

  (throw (Exception. "my exception message")) 

  ; VALIDATION
  (if (b/valid? order validator)

    ; SUCCESS
    (let [order (assoc order :number     (base32 5)
                             :price      (get PRICES (:box order))
                             :created-at (now))]

      (log/info "Order received:" order)

      (when EMAIL-ENABLED?
        (send-order-confirmation! order)
        (send-order-notification! order))

      (view/create order))

    ; FAILED
    (->> (b/validate order validator)
         second
         ::b/errors
         (assoc order :errors)
         view/show)))

(defroutes routes
  (GET  "/order" [box]   (view/show {:box box}))
  (POST "/order" [order] (create order)))
