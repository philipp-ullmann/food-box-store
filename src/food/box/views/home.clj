(ns food.box.views.home
  (:require [food.box.models.conf       :refer [PRICES SMALL REGULAR PREMIUM]]
            [food.box.views.application :refer :all]))

(defn- choose-button [value]
  [:form {:method "get" :action "/order"}
    [:input {:type "hidden" :name "box" :value value}]
    [:button.btn.btn-primary.btn-plan-select {:type "submit"} "Choose"]])

(defn home [conf]
  (layout conf
    [:div.pricing-table.pricing-three-column.row
      
      ; SMALL
      [:div#small-box.plan.col-sm-4.col-lg-4
        [:div.plan-name-bronze
          [:h2 "SMALL"]
          [:span (get PRICES SMALL) [:span " per box"]]]

        [:ul
          [:li.plan-feature "5 to 7 candies, chocolate or biscuit"]
          [:li.plan-feature "FREE shipping worldwide"]
          [:li.plan-feature (choose-button SMALL)]]]

      ; REGULAR
      [:div#regular-box.plan.col-sm-4.col-lg-4 {:style "z-index:55;"}
        [:div.plan-name-silver
          [:h2 "REGULAR"]
          [:span (get PRICES REGULAR) [:span " per box"]]]

        [:ul
          [:li.plan-feature "8 to 12 candies, chocolate, biscuit or chips"]
          [:li.plan-feature "1 soft drink"]
          [:li.plan-feature "FREE shipping worldwide"]
          [:li.plan-feature (choose-button REGULAR)]]]

      ; PREMIUM
      [:div#small-box.plan.col-sm-4.col-lg-4
        [:div.plan-name-gold
          [:h2 "PREMIUM"]
          [:span (get PRICES PREMIUM) [:span " per box"]]]

        [:ul
          [:li.plan-feature "13 to 17 candies, chocolate, biscuit or chips"]
          [:li.plan-feature "1 soft drink"]
          [:li.plan-feature "A special gift"]
          [:li.plan-feature "FREE shipping worldwide"]
          [:li.plan-feature (choose-button PREMIUM)]]]]

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
          [:p "We will immediately ship the food box after receipt of the payment. The delivery time depends on your location. It can last 1 to 2 weeks."]]]]))
