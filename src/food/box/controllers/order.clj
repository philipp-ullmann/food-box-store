(ns food.box.controllers.order
  (:require [food.box.views.order  :as view]
            [food.box.models.order :as o]
            [bouncer.core          :as b]
            [taoensso.timbre       :as log]
            [ring.util.response    :refer [redirect]]
            [compojure.core        :refer [defroutes GET POST]]))

(defn create
  "Validates an order and sends confirmation and notification emails."
  [order]

  ; VALIDATION
  (if (b/valid? order o/validator)

    ; SUCCESS
    (do
      (log/info "Order received:" order)
      (redirect "/"))

    ; FAILED
    (view/show order)))

(defroutes routes
  (GET  "/order" [box]   (view/show {:box box}))
  (POST "/order" [order] (create order)))
