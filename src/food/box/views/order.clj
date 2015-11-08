(ns food.box.views.order
  (:require [food.box.views.application :refer :all]))

(defn show
  "Renders the order formular."
  [box]
  (layout
    [:h1 "Order \"" box "\" box"]))
