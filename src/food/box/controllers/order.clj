(ns food.box.controllers.order
  (:require [food.box.views.order  :as view]
            [food.box.models.order :as o]
            [bouncer.core          :as b]
            [taoensso.timbre       :as log]
            [crypto.random         :refer [hex]]
            [ring.util.response    :refer [redirect]]
            [compojure.core        :refer [defroutes GET POST]]))

(defn create
  "Validates an order and sends confirmation and notification emails."
  [order]

  ; VALIDATION
  (if (b/valid? order o/validator)

    ; SUCCESS
    (let [order (assoc order :number (base32 5))]

      (log/info "Order received:" order)

      (-> (redirect "/")
          (assoc-in [:flash :notice]
                    (str "Thank you for your order! Order number: " (:number order)))))

    ; FAILED
    (->> (b/validate order o/validator)
         second
         ::b/errors
         (assoc order :errors)
         view/show)))

(defroutes routes
  (GET  "/order" [box]   (view/show {:box box}))
  (POST "/order" [order] (create order)))
