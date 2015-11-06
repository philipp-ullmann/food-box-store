(ns food.box.views.pricing
  (:require [food.box.views.application :refer :all]))

(defn show
  "Displays the pricing table."
  []
  (layout
    [:div.l-content
      [:div.pricing-tables.pure-g
        [:div.pure-u-1.pure-u-md-1-3
          [:div.pricing-table.pricing-table-free
            [:div.pricing-table-header
              [:h2 "Personal"]
              [:span.pricing-table-price "$5" [:span " per month"]]]

            [:ul.pricing-table-list
              [:li "Free setup"]
              [:li "Custom sub-domain"]
              [:li "Standard customer support"]
              [:li "1GB file storage"]
              [:li "1 database"]
              [:li "Unlimited bandwidth"]]

            [:button.button-choose.pure-button "Choose"]]]

        [:div.pure-u-1.pure-u-md-1-3
          [:div.pricing-table.pricing-table-biz.pricing-table-selected
            [:div.pricing-table-header
              [:h2 "Small Business"]
              [:span.pricing-table-price "$25" [:span " per month"]]]

            [:ul.pricing-table-list
              [:li "Free setup"]
              [:li "Use your own domain"]
              [:li "Standard customer support"]
              [:li "10GB file storage"]
              [:li "5 databases"]
              [:li "Unlimited bandwidth"]]

            [:button.button-choose.pure-button "Choose"]]]

        [:div.pure-u-1.pure-u-md-1-3
          [:div.pricing-table.pricing-table-enterprise
            [:div.pricing-table-header
              [:h2 "Enterprise"]
              [:span.pricing-table-price "$45" [:span " per month"]]]

            [:ul.pricing-table-list
              [:li "Free setup"]
              [:li "Use your own domain"]
              [:li "Premium customer support"]
              [:li "Unlimited file storage"]
              [:li "25 databases"]
              [:li "Unlimited bandwidth"]]

            [:button.button-choose.pure-button "Choose"]]]]]))
