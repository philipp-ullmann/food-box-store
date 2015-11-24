(ns food.box.views.mailer
  (:require [hiccup.core :refer [html]]))

(defn confirmation
  "Order confirmation template."
  [order]
  (html [:p "Thank you for you order number: "
            (:number order)]))
