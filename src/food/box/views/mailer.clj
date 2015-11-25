(ns food.box.views.mailer
  (:require [hiccup.core :refer [html]]))

(defn confirmation
  "Order confirmation template."
  [order]
  (html
    [:p "Thank you for ordering a " [:strong (:box order)] " box. "
        "Order number: " [:strong (:number order)]]

    [:p "Please transfer " [:strong (:price order)] " to:"]

    [:p "After payment receipt a " [:strong (:box order)] " box will be shiped as soon as possible to:"]
    
    [:p (str (:first-name order) " " (:last-name order)) [:br]
        (:street order) [:br]
        (str (:postcode order) " " (:city order)) [:br]
        (:country order)]

    [:p "Sincerely yours" [:br]
        "Alps food box team"]))
