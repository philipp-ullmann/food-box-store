(ns food.box.views.pricing
  (:require [environ.core               :refer [env]]
            [food.box.views.application :refer :all]))

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
              [:h2 "Personal"]
              [:span.pricing-table-price (:price-small env) [:span " per month"]]]

            [:ul.pricing-table-list
              [:li "Free setup"]
              [:li "Custom sub-domain"]
              [:li "Standard customer support"]
              [:li "1GB file storage"]
              [:li "1 database"]
              [:li "Unlimited bandwidth"]]

            [:button.button-choose.pure-button "Choose"]]]

        ; MEDIUM
        [:div.pure-u-1.pure-u-md-1-3
          [:div.pricing-table.pricing-table-biz.pricing-table-selected
            [:div.pricing-table-header
              [:h2 "Small Business"]
              [:span.pricing-table-price (:price-medium env) [:span " per month"]]]

            [:ul.pricing-table-list
              [:li "Free setup"]
              [:li "Use your own domain"]
              [:li "Standard customer support"]
              [:li "10GB file storage"]
              [:li "5 databases"]
              [:li "Unlimited bandwidth"]]

            [:button.button-choose.pure-button "Choose"]]]

        ; LARGE
        [:div.pure-u-1.pure-u-md-1-3
          [:div.pricing-table.pricing-table-enterprise
            [:div.pricing-table-header
              [:h2 "Enterprise"]
              [:span.pricing-table-price (:price-large env) [:span " per month"]]]

            [:ul.pricing-table-list
              [:li "Free setup"]
              [:li "Use your own domain"]
              [:li "Premium customer support"]
              [:li "Unlimited file storage"]
              [:li "25 databases"]
              [:li "Unlimited bandwidth"]]

            [:button.button-choose.pure-button "Choose"]]]]

      ; INFORMATIONS
      [:div.information.pure-g

        ; GET STARTED
        [:div.pure-u-1.pure-u-md-1-2
          [:div.l-box
            [:h3.information-head "Get started today"]
            [:p "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation."]]]

        ; PAYMENT
        [:div.pure-u-1.pure-u-md-1-2
          [:div.l-box
            [:h3.information-head "Pay monthly or annually"]
            [:p "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse."]]]

        ; SUPPORT
        [:div.pure-u-1.pure-u-md-1-2
          [:div.l-box
            [:h3.information-head "24/7 customer support"]
            [:p "Cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."]]]

        ; CANCEL
        [:div.pure-u-1.pure-u-md-1-2
          [:div.l-box
            [:h3.information-head "Cancel your plan anytime"]
            [:p "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."]]]]]))
