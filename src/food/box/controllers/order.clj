(ns food.box.controllers.order
  (:require [food.box.views.order :as view]
            [compojure.core       :refer [defroutes GET POST]]))

(defroutes routes
  (GET "/order" [box] (view/show box)))
