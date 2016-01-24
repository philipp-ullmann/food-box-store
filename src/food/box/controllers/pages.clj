(ns food.box.controllers.pages
  (:require [compojure.core :refer [defroutes GET]]
            [food.box.views [home     :refer [home]]
                            [about-us :refer [about-us]]
                            [terms    :refer [terms]]]))

(defroutes routes
  (GET "/" [] (home {:nav-types? true}))
  (GET "/about_us" [] (about-us {:nav-about? true}))
  (GET "/terms_and_conditions" [] (terms {})))
