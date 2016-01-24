(ns food.box.views.home
  (:require [food.box.models.conf       :refer [PRICES SMALL REGULAR PREMIUM]]
            [food.box.views.application :refer :all]))

(defn- price-card [name type items]
  [:div.col-sm-4
    [:div.card
      [:div.card-block
        [:h4.card-title name]
        [:p.card-text (get PRICES type) [:span " per box"]]]

      [:ul.list-group.list-group-flush
        (map #(vector :li.list-group-item %) items)]

      [:div.card-block
        [:form {:method "get" :action "/order"}
          [:input {:type "hidden" :name "box" :value type}]
          [:button.btn.btn-primary.btn-plan-select {:type "submit"} "Choose"]]]]])

(defn- desc-item [title & desc]
  [:div.col-sm-6 [:h4 title] [:p desc]])

(defn home [conf]
  (layout conf

    ; PRICING CARD
    [:div.row.pricing-table
      (price-card "SMALL" SMALL
        ["5 to 7 candies, chocolate or biscuit"
         "FREE shipping worldwide"])

      (price-card "REGULAR" REGULAR
        ["8 to 12 candies, chocolate, biscuit or chips"
         "1 soft drink"
         "FREE shipping worldwide"])

      (price-card "PREMIUM" PREMIUM
        ["13 to 17 candies, chocolate, biscuit or chips"
         "1 soft drink"
         "A special gift"
         "FREE shipping worldwide"])]

    ; DESCRIPTION
    [:div.row
      (desc-item "HOW IT WORKS"
        "You choose one of the food box sizes. Provide the shipping information and confirm the order. You will receive immediately a confirmation email with a summary of your order and an order number. After receipt of payment, we will ship the food box.")

      (desc-item "PAYMENT"
        "After a successful order, you will receive an order confirmation email with the bank account information. Please provide the order number under payment reference on the transaction. At the time no credit card payment is possible. Soon paypal payment will be supported.")

      (desc-item "CUSTOMER SUPPORT"
        "If you have any further questions, please feel free to " [:a {:href "/contact"} "contact"] " us. You will receive an answer within a few days.")

      (desc-item "DELIVERY"
        "We will immediately ship the food box after receipt of the payment. The delivery time depends on your location. It can last 1 to 2 weeks.")]))
