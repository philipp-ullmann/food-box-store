(defproject food.box.store "0.1.0-SNAPSHOT"
  :description "Vienna Sweety food box store."
  :url "https://bitbucket.org/philipp-ullmann/vienna-sweety/overview"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure        "1.7.0"]
                 [ring/ring-jetty-adapter    "1.4.0"]
                 [compojure                  "1.4.0"]
                 [hiccup                     "1.0.5"]
                 [environ                    "1.0.1"]
                 [bouncer                    "0.3.3"]
                 [com.taoensso/timbre        "4.1.4"]
                 [com.draines/postal         "1.11.3"]
                 [clj-time                   "0.11.0"]
                 [de.bertschneider/clj-geoip "0.2"]]
  :main ^:skip-aot food.box.store
  :uberjar-name "vienna-sweety-standalone.jar"
  :target-path "target/%s"
  :min-lein-version "2.0.0"
  :plugins [[environ/environ.lein "0.3.1"]]
  :hooks [environ.leiningen.hooks]
  :profiles {:uberjar {:aot :all}
             :dev     {:dependencies [[kerodon   "0.7.0"]]
                       :plugins      [[lein-ring "0.9.7"]]

                       :ring {:handler food.box.store/app
                              :port    8080}}
             :production {:env {:production true}}})
