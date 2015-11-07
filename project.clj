(defproject food.box.store "0.1.0-SNAPSHOT"
  :description "Austrian food box store."
  :url "https://bitbucket.org/philipp-ullmann/food-box-store/overview"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure     "1.7.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [compojure               "1.4.0"]
                 [hiccup                  "1.0.5"]
                 [environ                 "1.0.1"]]
  :main ^:skip-aot food.box.store
  :uberjar-name "store-standalone.jar"
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :test    {:dependencies [[kerodon "0.7.0"]]}})
