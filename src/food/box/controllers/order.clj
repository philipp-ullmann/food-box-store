(ns food.box.controllers.order
  (:require [taoensso.timbre      :as log]
            [food.box.views.order :as view]
            [bouncer.core         :as b]
            [crypto.random        :refer [base32]]
            [ring.util.response   :refer [redirect]]
            [compojure.core       :refer [defroutes GET POST]]
            [clj-time.core        :refer [now]]

            [food.box.models [order  :refer [validator]]
                             [conf   :refer [PRICES]]
                             [mailer :refer [send-order-confirmation!
                                             send-order-notification!]]]))

(defn create
  "Validates an order and sends confirmation and notification emails."
  [order]

  ; VALIDATION
  (if (b/valid? order validator)

    ; SUCCESS
    (let [order (assoc order :number     (base32 5)
                             :price      (get PRICES (:box order))
                             :created-at (now))]

      (log/info "Order received:" order)
      (send-order-confirmation! order)
      (send-order-notification! order)

      (-> (redirect "/")
          (assoc-in [:flash :notice]
                    (str "Thank you for your order! Order number: " (:number order)))))

    ; FAILED
    (->> (b/validate order validator)
         second
         ::b/errors
         (assoc order :errors)
         view/show)))

(defroutes routes
  (GET  "/order" [box]   (view/show {:box box}))
  (POST "/order" [order] (create order)))
