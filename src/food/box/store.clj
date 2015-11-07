(ns food.box.store
  (:require [environ.core           :refer [env]]
            [ring.adapter.jetty     :refer [run-jetty]]
            [food.box.views.pricing :as pricing]
            [compojure [core    :refer [defroutes GET]]
                       [route   :refer [resources]]
                       [handler :as handler]])
            
  (:gen-class))

(defroutes routes
  (GET "/" [] (pricing/show))
  (resources "/"))

(def app
  (handler/site routes))

(defn -main [& args]
  (run-jetty app {:port  (Integer. (:port env))
                  :join? false}))
