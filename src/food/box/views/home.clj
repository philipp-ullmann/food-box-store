(ns food.box.views.home
  (:require [food.box.models.conf       :refer [PRICES SMALL REGULAR PREMIUM]]
            [food.box.views.application :refer :all]))

(defn- choose-button
  "Returns a form button to choose a food box."
  [value]
  [:form {:method "get" :action "/order"}
    [:input {:type "hidden" :name "box" :value value}]
    [:button.button-choose.pure-button {:type "submit"} "Choose"]])

(defn home
  "Displays the pricing table."
  [conf]
  (layout conf
    [:div.l-content
      
      ; PRICES
      [:div.pricing-tables.pure-g

        ; SMALL
        [:div#small-box.pure-u-1.pure-u-md-1-3
          [:div.pricing-table.pricing-table-free
            [:div.pricing-table-header
              [:h2 "SMALL"]
              [:span.pricing-table-price (get PRICES SMALL)
                                         [:span " per box"]]]

            [:ul.pricing-table-list
              [:li "5 to 7 candies, chocolate or biscuit"]
              [:li "Something from the nature"]
              [:li "FREE shipping worldwide"]]

            (choose-button SMALL)]]

        ; REGULAR
        [:div#regular-box.pure-u-1.pure-u-md-1-3
          [:div.pricing-table.pricing-table-biz.pricing-table-selected
            [:div.pricing-table-header
              [:h2 "REGULAR"]
              [:span.pricing-table-price (get PRICES REGULAR)
                                         [:span " per box"]]]

            [:ul.pricing-table-list
              [:li "8 to 12 candies, chocolate, biscuit or chips"]
              [:li "1 soft drink"]
              [:li "Something from the nature"]
              [:li "FREE shipping worldwide"]]

            (choose-button REGULAR)]]

        ; PREMIUM
        [:div#premium-box.pure-u-1.pure-u-md-1-3
          [:div.pricing-table.pricing-table-enterprise
            [:div.pricing-table-header
              [:h2 "PREMIUM"]
              [:span.pricing-table-price (get PRICES PREMIUM)
                                         [:span " per box"]]]

            [:ul.pricing-table-list
              [:li "13 to 17 candies, chocolate, biscuit or chips"]
              [:li "1 soft drink"]
              [:li "A special gift"]
              [:li "Something from the nature"]
              [:li "FREE shipping worldwide"]]

            (choose-button PREMIUM)]]]

      ; INFORMATIONS
      [:div.information.pure-g

        ; DESCRIPTION
        [:div.pure-u-1.pure-u-md-1-2
          [:div.l-box
            [:h3.information-head "How it works"]
            [:p "You choose one of the food box sizes. Provide the shipping information and confirm the order. You will receive immediately a confirmation email with a summary of your order and an order number. After receipt of payment, we will ship the food box."]]]

        ; PAYMENT
        [:div.pure-u-1.pure-u-md-1-2
          [:div.l-box
            [:h3.information-head "Payment"]
            [:p "After a successful order, you will receive an order confirmation email with the bank account information. Please provide the order number under payment reference on the transaction. At the time no credit card payment is possible. Soon paypal payment will be supported."]]]

        ; SUPPORT
        [:div.pure-u-1.pure-u-md-1-2
          [:div.l-box
            [:h3.information-head "Customer support"]
            [:p "If you have any further questions, please feel free to " [:a {:href "/contact"} "contact"] " us. You will receive an answer within a few days."]]]

        ; DELIVERY
        [:div.pure-u-1.pure-u-md-1-2
          [:div.l-box
            [:h3.information-head "DELIVERY"]
            [:p "We will immediately ship the food box after receipt of the payment. The delivery time depends on your location. It can last 1 to 2 weeks."]]]]]))
