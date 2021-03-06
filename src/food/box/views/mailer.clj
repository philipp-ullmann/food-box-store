(ns food.box.views.mailer
  (:require [hiccup.page          :refer [html5]]
            [food.box.views.order :refer [summary-partial]]))

(defn confirmation
  "Order confirmation template."
  [order]
  (html5
    (summary-partial order)
    [:p "Sincerely yours" [:br]
        "ViennaSweety team"]))

(defn notification
  "Order notification template."
  [{:keys [box number price created-at first-name last-name email street postcode city country]}]
  (html5
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
  "Exception message template."
  [exception]
  (html5
    [:p [:strong exception]]
    [:p (interpose [:br] (.getStackTrace exception))]))

(defn contact
  "Contact message template."
  [{:keys [name email created-at message]}]
  (html5
    [:p "Name: "       name]
    [:p "Email: "      email]
    [:p "Created at: " created-at]
    [:p message]))
