(ns food.box.views.mailer
  (:require [hiccup.core          :refer [html]]
            [food.box.views.order :refer [summary-partial]]))

(defn confirmation
  "Order confirmation template."
  [order]
  (html (summary-partial order)
        [:p "Sincerely yours" [:br]
            "Alps food box team"]))

(defn notification
  "Order notification template."
  [{:keys [box number price created-at first-name last-name email street postcode city country]}]
  (html
    [:p "Order number: "  number]
    [:p "Box size: "      box]
    [:p "Price: "         price]
    [:p "First name: "    first-name]
    [:p "Last name: "     last-name]
    [:p "Email address: " email]
    [:p "Street: "        street]
    [:p "Postcode: "      postcode]
    [:p "City: "          city]
    [:p "Country: "       country]
    [:p "Created at: "    created-at]))

(defn exception
  "Exception message."
  [exception]
  (html
    [:p [:strong exception]]
    [:p (interpose [:br] (.getStackTrace exception))]))

