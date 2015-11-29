(ns food.box.store
  (:require [ring.adapter.jetty         :refer [run-jetty]]
            [food.box.middleware        :refer [wrap-exception]]
            [food.box.models.conf       :refer [PORT]]
            [food.box.views.application :as view]

            [ring.middleware [params         :refer [wrap-params]]
                             [nested-params  :refer [wrap-nested-params]]
                             [keyword-params :refer [wrap-keyword-params]]]

            [compojure [core  :refer [defroutes]]
                       [route :refer [resources not-found]]]

            [food.box.controllers [pricing :as pricing]
                                  [order   :as order]])
            
  (:gen-class))

(defroutes routes
  pricing/routes
  order/routes
  (resources "/")
  (not-found
    (view/error
      "The page you were looking for doesn't exist."
      "You may have mistyped the address or the page may have moved."))) 

(def app
  (-> routes
      wrap-exception
      wrap-keyword-params
      wrap-nested-params
      wrap-params))

(defn -main [& args]
  (run-jetty app {:port  PORT
                  :join? false}))
