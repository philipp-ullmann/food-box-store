(ns food.box.controllers.terms
  (:require [compojure.core       :refer [defroutes GET]]
            [food.box.views.terms :as view]))

(defroutes routes
  (GET "/terms_and_conditions" [] (view/show)))
