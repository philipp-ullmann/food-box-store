(ns food.box.controllers.order
  (:require [taoensso.timbre      :as log]
            [food.box.views.order :as view]
            [bouncer.core         :as b]
            [crypto.random        :refer [base32]]
            [ring.util.response   :refer [redirect]]
            [compojure.core       :refer [defroutes GET POST]]

            [food.box.models [order :refer [validator]]
                             [conf  :refer [PRICES]]]))

(defn create
  "Validates an order and sends confirmation and notification emails."
  [order]

  ; VALIDATION
  (if (b/valid? order validator)

    ; SUCCESS
    (let [order (assoc order :number (base32 5)
                             :price  (get PRICES (:box order)))]

      (log/info "Order received:" order)

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
