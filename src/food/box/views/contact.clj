(ns food.box.views.contact
  (:require [food.box.views.application :refer :all]))

(defn show
  "Renders the contact formular."
  [{:keys [email name message]}]
  (layout
    [:div.l-box
      [:h1 "Contact"]]))
