(ns food.box.controllers.order
  (:require [food.box.views.order :as view]
            [bouncer.core         :as b]
            [ring.util.response   :refer [redirect]]
            [compojure.core       :refer [defroutes GET POST]]))

(defn create
  "Validates an order and sends confirmation and notification emails."
  [order]
  (if (b/valid? order)
    (redirect "/")

    (view/show box)))

(defroutes routes
  (GET  "/order" [box]   (view/show box))
  (POST "/order" [order] (create order)))
