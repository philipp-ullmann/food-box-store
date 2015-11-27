(defproject food.box.store "0.1.0-SNAPSHOT"
  :description "Alps food box store."
  :url "https://bitbucket.org/philipp-ullmann/food-box-store/overview"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure     "1.7.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [compojure               "1.4.0"]
                 [hiccup                  "1.0.5"]
                 [environ                 "1.0.1"]
                 [bouncer                 "0.3.3"]
                 [com.taoensso/timbre     "4.1.4"]
                 [com.draines/postal      "1.11.3"]
                 [clj-time                "0.11.0"]]
  :main ^:skip-aot food.box.store
  :uberjar-name "store-standalone.jar"
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :test    {:dependencies [[kerodon "0.7.0"]]}})
