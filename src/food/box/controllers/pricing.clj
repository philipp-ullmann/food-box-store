(ns food.box.controllers.pricing
  (:require [food.box.views.pricing :as view]
            [compojure.core         :refer [defroutes GET]]))

(defroutes routes
  (GET "/" [] (view/show {:banner? true :menu-types? true})))
