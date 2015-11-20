(ns food.box.matcher
  (:require [kerodon.impl :refer :all]
            [kerodon.test :refer :all]))

(defmacro checked? [selector]
  `(validate =
             #(-> (:enlive %)
                  (form-element-for ~selector)
                  first
                  :attrs
                  (get :checked))
             "on"
             (~'checked? ~selector :checked "on")))
