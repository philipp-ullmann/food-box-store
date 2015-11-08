(ns food.box.store
  (:require [environ.core       :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]]

            [compojure [core    :refer [defroutes]]
                       [route   :refer [resources]]
                       [handler :as handler]]

            [food.box.controllers [pricing :as pricing]
                                  [order   :as order]])
            
  (:gen-class))

(defroutes routes
  pricing/routes
  order/routes
  (resources "/"))

(def app
  (handler/site routes))

(defn -main [& args]
  (run-jetty app {:port  (Integer. (:port env))
                  :join? false}))
