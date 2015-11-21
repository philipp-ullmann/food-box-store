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

(defmacro selected? [selector value]
  `(validate =
             #(let [options# (-> (form-element-for (:enlive %) ~selector)
                                 first
                                 :content)]

                   (some (fn [option#]
                           (and (= ~value     (first (:content option#)))
                                (= "selected" (get-in option# [:attrs :selected]))))
                         options#))
             true
             (~'selected? ~selector ~value)))

