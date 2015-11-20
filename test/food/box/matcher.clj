(ns food.box.matcher
  (:require [kerodon.impl :refer :all]
            [kerodon.test :refer :all]))

(defmacro checked? [selector]
  `(validate not=
             #(get-value % ~selector)
             nil
             (~'checked? ~selector)))
