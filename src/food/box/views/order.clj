(ns food.box.views.order
  (:require [food.box.views.application :refer :all]
            [food.box.models.country    :refer [COUNTRIES]]
            [food.box.models.conf       :refer [BANK-ACCOUNT]]))

(defn- country-options
  "Returns the option tags for all available countries."
  [current]
  (map #(vector :option {:selected (= current %)} %)
       COUNTRIES))

(defn- errors-for
  "Renders all errors for an attribute."
  [errors]
  (when errors (map #(vector :li %) errors)))

(defn show
  "Renders the order formular."
  [{:keys [box first-name last-name email street postcode city country terms-accepted errors]}]
  (layout
    [:div.l-box
      [:h1 "Order \"" box "\" box"]

      (if errors
        [:ul.alert-box
          (map #(errors-for (% errors))
               [:first-name
                :last-name
                :email
                :street
                :postcode
                :city
                :country
                :terms-accepted
                :box])])

      [:form.pure-form.pure-form-aligned {:method "post" :action "/order"}
        [:fieldset
          [:input#box {:type  "hidden"
                       :name  "order[box]"
                       :value box}]

          [:div.pure-control-group
            [:label {:for "first-name"} "First Name *"]
            [:input#first-name {:type     "text"
                                :name     "order[first-name]"
                                :value    first-name
                                :required true}]]
      
          [:div.pure-control-group
            [:label {:for "last-name"} "Last Name *"]
            [:input#last-name {:type     "text"
                               :name     "order[last-name]"
                               :value    last-name
                               :required true}]]
      
          [:div.pure-control-group
            [:label {:for "email"} "Email Address *"]
            [:input#email {:type     "email"
                           :name     "order[email]"
                           :value    email
                           :required true}]]
      
          [:div.pure-control-group
            [:label {:for "street"} "Street *"]
            [:input#street {:type     "text"
                            :name     "order[street]"
                            :value    street
                            :required true}]]

          [:div.pure-control-group
            [:label {:for "postcode"} "Postcode / Zip *"]
            [:input#postcode {:type     "text"
                              :name     "order[postcode]"
                              :value    postcode
                              :required true}]]

          [:div.pure-control-group
            [:label {:for "city"} "Town / City *"]
            [:input#city {:type     "text"
                          :name     "order[city]"
                          :value    city
                          :required true}]]

          [:div.pure-control-group
            [:label {:for "country"} "Country *"]
            [:select#country {:name "order[country]"}
                             (country-options country)]]
      
          [:div.pure-control-group
            [:label.pure-checkbox {:for "terms-accepted"}
              [:input#terms-accepted {:type    "checkbox"
                                      :name    "order[terms-accepted]"
                                      :checked terms-accepted}
                                     " I've read the terms and conditions"]]
      
            [:button.pure-button.pure-button-primary {:type "submit"} "Confirm"]
            " | "
            [:a {:href "/"} "Cancel"]]]]]))

(defn summary-partial
  "Order summary and payment instruction for the customer."
  [{:keys [box number price first-name last-name email street postcode city country]}]
  [:div
    [:p "Thank you for ordering a " [:strong box] " box. "
        "Your order number is: " [:strong number]]

    [:p "Please transfer " [:strong price] " to:"]

    [:p "Bank name: "         (:bank    BANK-ACCOUNT) [:br]
        "Country:   "         (:country BANK-ACCOUNT) [:br]
        "Owner: "             (:owner   BANK-ACCOUNT) [:br]
        "BIC: "               (:bic     BANK-ACCOUNT) [:br]
        "IBAN: "              (:iban    BANK-ACCOUNT) [:br]
        "Payment reference: " number]

    [:p "After payment receipt a " [:strong box] " box will be shiped as soon as possible to:"]
    
    [:p (str first-name " " last-name) [:br]
        street [:br]
        (str postcode " " city) [:br]
        country]])

(defn create
  "Renders an order confirmation message."
  [order]
  (layout (summary-partial order)
          [:p "An order confirmation has been sent to: "
              [:strong (:email order)]]
          [:p [:a {:href "/"} "Main page"]
              " | "
              [:a {:href "javascript:window.print()"} "Print"]]))

