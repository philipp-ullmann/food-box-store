(ns food.box.store-test
  (:require [kerodon.core   :refer :all]
            [kerodon.test   :refer :all]
            [clojure.test   :refer :all]
            [environ.core   :refer [env]]
            [food.box.store :refer [app]])) 

(deftest user-can-see-prices-of-food-boxes
  (-> (session app)
      (visit "/")
      (has (some-text? (:price-small env)))
      (has (some-text? (:price-medium env)))
      (has (some-text? (:price-large env)))))
