(ns food.box.views.order
  (:require [clojure.string             :refer [blank?]]
            [food.box.views.application :refer :all]
            [food.box.models.country    :refer [COUNTRIES]]
            [food.box.models.conf       :refer [BANK-ACCOUNT]]))

(defn- country-options
  "Returns the option tags for all available countries."
  [current]
  (map #(vector :option {:value (first %) :selected (if (= current (first %)) "selected")} (second %))
       COUNTRIES))

(defn show
  "Renders the order formular."
  [{:keys [box first-name last-name email street postcode city country terms-accepted errors]} conf]
  (layout conf 
    [:div.page-header [:h1 "Order a \"" box "\" box"]]

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

    [:form {:method "post" :action "/order"}
      [:input#box {:type  "hidden"
                   :name  "order[box]"
                   :value box}]

      [:fieldset.form-group
        [:label {:for "first-name"} "First Name *"]
        [:input.form-control {:type     "text"
                              :id       "first-name"
                              :name     "order[first-name]"
                              :value    first-name
                              :required true}]]
    
      [:fieldset.form-group
        [:label {:for "last-name"} "Last Name *"]
        [:input.form-control {:type     "text"
                              :id       "last-name"
                              :name     "order[last-name]"
                              :value    last-name
                              :required true}]]
    
      [:fieldset.form-group
        [:label {:for "email"} "Email Address *"]
        [:input.form-control {:type     "email"
                              :id       "email"
                              :name     "order[email]"
                              :value    email
                              :required true}]]
    
      [:fieldset.form-group
        [:label {:for "street"} "Street *"]
        [:input.form-control {:type     "text"
                              :id       "street"
                              :name     "order[street]"
                              :value    street
                              :required true}]]

      [:fieldset.form-group
        [:label {:for "postcode"} "Postcode / Zip *"]
        [:input.form-control {:type     "text"
                              :id       "postcode"
                              :name     "order[postcode]"
                              :value    postcode
                              :required true}]]

      [:fieldset.form-group
        [:label {:for "city"} "Town / City *"]
        [:input.form-control {:type     "text"
                              :id       "city"
                              :name     "order[city]"
                              :value    city
                              :required true}]]

      [:fieldset.form-group
        [:label {:for "country"} "Country *"]
        [:select.form-control {:id   "country"
                               :name "order[country]"}
                              (country-options country)]]

      [:div.checkbox
        [:label {:for "terms-accepted"}
          [:input#terms-accepted {:type    "checkbox"
                                  :name    "order[terms-accepted]"
                                  :checked terms-accepted}

                                 " I've read the "
                                 [:a {:href   "/terms_and_conditions"
                                      :target "_blank"}
                                     "terms and conditions"]]]]

        [:button.btn.btn-primary {:type "submit"} "Submit"]
        " | "
        [:a {:href "/"} "Cancel"]]))

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
  [order conf]
  (layout conf
    [:div.l-box
      (summary-partial order)

      [:p "An order confirmation has been sent to: "
          [:strong (:email order)]]
      [:p [:a {:href "/"} "<< Main page"]
          " | "
          [:a {:href "javascript:window.print()"} "Print"]]]))

