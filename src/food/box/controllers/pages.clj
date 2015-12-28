(ns food.box.controllers.pages
  (:require [compojure.core :refer [defroutes GET]]
            [food.box.views [home     :refer [home]]
                            [about-us :refer [about-us]]
                            [terms    :refer [terms]]]))

(defroutes routes
  (GET "/" [] (home {:banner? true :menu-types? true}))
  (GET "/about_us" [] (about-us {:menu-about? true}))
  (GET "/terms_and_conditions" [] (terms {})))
