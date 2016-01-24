(ns food.box.store
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [food.box.middleware :refer [wrap-exception
                                         wrap-location]]
            [food.box.models.conf :refer [port]]
            [food.box.views.application :as view]

            [ring.middleware [params         :refer [wrap-params]]
                             [nested-params  :refer [wrap-nested-params]]
                             [keyword-params :refer [wrap-keyword-params]]]

            [compojure [core  :refer [defroutes]]
                       [route :refer [resources not-found]]]

            [food.box.controllers [pages   :as pages]
                                  [order   :as order]
                                  [contact :as contact]])
            
  (:gen-class))

(defroutes routes
  pages/routes
  order/routes
  contact/routes
  (resources "/")
  (not-found
    (view/error
      "The page you were looking for doesn't exist."
      "You may have mistyped the address or the page may have moved."))) 

(def app
  (-> routes
      wrap-exception
      wrap-location
      wrap-keyword-params
      wrap-nested-params
      wrap-params))

(defn -main [& args]
  (run-jetty app {:port  (port)
                  :join? false}))
