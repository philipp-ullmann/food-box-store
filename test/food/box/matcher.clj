(ns food.box.matcher
  (:require [kerodon.impl :refer :all]
            [kerodon.test :refer :all]))

(defmacro checked? [selector]
  `(validate =
             #(get-attr % ~selector :checked)
             "checked"
             (~'checked? ~selector :checked "checked")))
