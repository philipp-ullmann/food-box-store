(ns food.box.store
  (:require [environ.core       :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]]

            [ring.middleware [params         :refer [wrap-params]]
                             [nested-params  :refer [wrap-nested-params]]
                             [keyword-params :refer [wrap-keyword-params]]]

            [compojure [core  :refer [defroutes]]
                       [route :refer [resources]]]

            [food.box.controllers [pricing :as pricing]
                                  [order   :as order]])
            
  (:gen-class))

(defroutes routes
  pricing/routes
  order/routes
  (resources "/"))

(def app
  (-> routes
      wrap-keyword-params
      wrap-nested-params
      wrap-params))

(defn -main [& args]
  (run-jetty app {:port  (Integer. (:port env))
                  :join? false}))
