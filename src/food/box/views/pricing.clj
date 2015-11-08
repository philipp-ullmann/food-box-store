(ns food.box.views.pricing
  (:require [environ.core               :refer [env]]
            [food.box.models.order      :as order]
            [food.box.views.application :refer :all]))

(defn- choose-button
  "Returns a form button to choose a food box."
  [value]
  [:form {:method "get" :action "/order"}
    [:input {:type "hidden" :name "box" :value value}]
    [:input.button-choose.pure-button {:type "submit" :value "Choose"}]])

(defn show
  "Displays the pricing table."
  []
  (layout
    [:div.l-content
      
      ; PRICES
      [:div.pricing-tables.pure-g

        ; SMALL
        [:div.pure-u-1.pure-u-md-1-3
          [:div.pricing-table.pricing-table-free
            [:div.pricing-table-header
              [:h2 "SMALL"]
              [:span.pricing-table-price (:price-small env) [:span " per box"]]]

            [:ul.pricing-table-list
              [:li "FREE shipping worldwide"]
              [:li "..."]
              [:li "..."]
              [:li "..."]
              [:li "..."]
              [:li "..."]]

            (choose-button order/SMALL)]]

        ; REGULAR
        [:div.pure-u-1.pure-u-md-1-3
          [:div.pricing-table.pricing-table-biz.pricing-table-selected
            [:div.pricing-table-header
              [:h2 "REGULAR"]
              [:span.pricing-table-price (:price-regular env) [:span " per box"]]]

            [:ul.pricing-table-list
              [:li "FREE shipping worldwide"]
              [:li "..."]
              [:li "..."]
              [:li "..."]
              [:li "..."]
              [:li "..."]]

            (choose-button order/REGULAR)]]

        ; PREMIUM
        [:div.pure-u-1.pure-u-md-1-3
          [:div.pricing-table.pricing-table-enterprise
            [:div.pricing-table-header
              [:h2 "PREMIUM"]
              [:span.pricing-table-price (:price-premium env) [:span " per box"]]]

            [:ul.pricing-table-list
              [:li "FREE shipping worldwide"]
              [:li "..."]
              [:li "..."]
              [:li "..."]
              [:li "..."]
              [:li "..."]]

            (choose-button order/PREMIUM)]]]

      ; INFORMATIONS
      [:div.information.pure-g

        ; DESCRIPTION
        [:div.pure-u-1.pure-u-md-1-2
          [:div.l-box
            [:h3.information-head "How it works"]
            [:p "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation."]]]

        ; PAYMENT
        [:div.pure-u-1.pure-u-md-1-2
          [:div.l-box
            [:h3.information-head "Payment"]
            [:p "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse."]]]

        ; SUPPORT
        [:div.pure-u-1.pure-u-md-1-2
          [:div.l-box
            [:h3.information-head "Customer support"]
            [:p "Cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."]]]

        ; DELIVERY
        [:div.pure-u-1.pure-u-md-1-2
          [:div.l-box
            [:h3.information-head "DELIVERY"]
            [:p "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."]]]]]))
