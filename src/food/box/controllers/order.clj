(ns food.box.controllers.order
  (:require [taoensso.timbre      :as log]
            [food.box.views.order :as view]
            [bouncer.core         :refer [valid?]]
            [crypto.random        :refer [base32]]
            [compojure.core       :refer [defroutes GET POST]]

            [food.box.models [country :refer [COUNTRIES]]
                             [utils   :refer [assoc-errors now]]
                             [order   :refer [validator]]
                             [conf    :refer [PRICES EMAIL-ENABLED?]]
                             [mailer  :refer [send-order-confirmation!
                                              send-order-notification!]]]))

(defn show
  "Shows the order formular."
  [order]
  (view/show order {:menu-types? true}))

(defn create!
  "Validates an order and sends confirmation and notification emails."
  [order]

  ; VALIDATION
  (if (valid? order validator)

    ; SUCCESS
    (let [order (assoc order :country    (get COUNTRIES (:country order))
                             :number     (base32 5)
                             :price      (get PRICES (:box order))
                             :created-at (now))]

      (log/info "Order received:" order)

      (when EMAIL-ENABLED?
        (send-order-confirmation! order)
        (send-order-notification! order))

      (view/create order {:menu-types? true}))

    ; FAILED
    (show (assoc-errors order validator))))

(defroutes routes
  (GET "/order" [box :as req]
    (show {:box box :country (get-in req [:location :country-code])}))

  (POST "/order" [order] (create! order)))
