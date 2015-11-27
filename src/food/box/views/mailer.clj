(ns food.box.views.mailer
  (:require [hiccup.core          :refer [html]]
            [food.box.models.conf :refer [BANK-ACCOUNT]]))

(defn confirmation
  "Order confirmation template."
  [{:keys [box number price first-name last-name email street postcode city country]}]
  (html
    [:p "Thank you for ordering a " [:strong box] " box. "
        "Your order number is: " [:strong number]]

    [:p "Please transfer " [:strong price] " to:"]

    [:p "Bank name: "         (:bank    BANK-ACCOUNT) [:br]
        "Country:   "         (:country BANK-ACCOUNT) [:br]
        "Owner: "             (:owner   BANK-ACCOUNT) [:br]
        "BIC: "               (:bic     BANK-ACCOUNT) [:br]
        "IBAN: "              (:iban    BANK-ACCOUNT) [:br]
        "Payment reference: " (:number order)]

    [:p "After payment receipt a " [:strong box] " box will be shiped as soon as possible to:"]
    
    [:p (str first-name " " last-name) [:br]
        street [:br]
        (str postcode " " city) [:br]
        country]

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
