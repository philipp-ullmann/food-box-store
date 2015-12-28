(ns food.box.controllers.pages
  (:require [compojure.core :refer [defroutes GET]]
            [food.box.views [home  :refer [home]]
                            [terms :refer [terms]]]))

(defroutes routes
  (GET "/" [] (home {:banner? true :menu-types? true}))
  (GET "/terms_and_conditions" [] (terms {})))
