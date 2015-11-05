(ns food.box.store
  (:require [environ.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]]
            [net.cgrand.enlive-html :as html]
            [compojure [core :refer [defroutes GET]]
                       [route :refer [resources]]
                       [handler :as handler]])
            
  (:gen-class))

(html/deftemplate home "views/application.html" [])

(defroutes routes
  (GET "/" [] (home))
  (resources "/"))

(def app
  (handler/site routes))

(defn -main [& args]
  (run-jetty app {:port  (Integer. (:port env))
                  :join? false}))
