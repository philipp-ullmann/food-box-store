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

    [:form {:method "post" :action "/order"}
      [:input#box {:type  "hidden"
                   :name  "order[box]"
                   :value box}]

      [(if (:first-name errors) :fieldset.form-group.has-danger :fieldset.form-group)
        [:label.form-control-label {:for "first-name"} "First Name *"]

        [(if (:first-name errors) :input.form-control.form-control-danger :input.form-control)
           {:type     "text"
            :id       "first-name"
            :name     "order[first-name]"
            :value    first-name
            :required true}]
        
        (errors-for (:first-name errors))]
    
      [(if (:last-name errors) :fieldset.form-group.has-danger :fieldset.form-group)
        [:label.form-control-label {:for "last-name"} "Last Name *"]

        [(if (:last-name errors) :input.form-control.form-control-danger :input.form-control)
          {:type     "text"
           :id       "last-name"
           :name     "order[last-name]"
           :value    last-name
           :required true}]

        (errors-for (:last-name errors))]
    
      [(if (:email errors) :fieldset.form-group.has-danger :fieldset.form-group)
        [:label.form-control-label {:for "email"} "Email Address *"]

        [(if (:email errors) :input.form-control.form-control-danger :input.form-control)
          {:type     "email"
           :id       "email"
           :name     "order[email]"
           :value    email
           :required true}]

        (errors-for (:email errors))]
    
      [(if (:street errors) :fieldset.form-group.has-danger :fieldset.form-group)
        [:label.form-control-label {:for "street"} "Street *"]

        [(if (:street errors) :input.form-control.form-control-danger :input.form-control)
          {:type     "text"
           :id       "street"
           :name     "order[street]"
           :value    street
           :required true}]

        (errors-for (:street errors))]

      [(if (:postcode errors) :fieldset.form-group.has-danger :fieldset.form-group)
        [:label.form-control-label {:for "postcode"} "Postcode / Zip *"]

        [(if (:postcode errors) :input.form-control.form-control-danger :input.form-control)
          {:type     "text"
           :id       "postcode"
           :name     "order[postcode]"
           :value    postcode
           :required true}]

        (errors-for (:postcode errors))]

      [(if (:city errors) :fieldset.form-group.has-danger :fieldset.form-group)
        [:label.form-control-label {:for "city"} "Town / City *"]

        [(if (:city errors) :input.form-control.form-control-danger :input.form-control)
          {:type     "text"
           :id       "city"
           :name     "order[city]"
           :value    city
           :required true}]

        (errors-for (:city errors))]

      [(if (:country errors) :fieldset.form-group.has-danger :fieldset.form-group)
        [:label.form-control-label {:for "country"} "Country *"]

        [(if (:country errors) :select.form-control.form-control-danger :select.form-control)
          {:id   "country"
           :name "order[country]"}
          (country-options country)]

        (errors-for (:country errors))]

      [(if-not (:terms-accepted errors) :div.checkbox.has-danger :div.checkbox)
        [:label.form-control-label {:for "terms-accepted"}
          [:input#terms-accepted {:type    "checkbox"
                                  :name    "order[terms-accepted]"
                                  :checked terms-accepted}

                                 " I've read the "
                                 [:a {:href   "/terms_and_conditions"
                                      :target "_blank"}
                                     "terms and conditions"]]]

        (errors-for ["must be accepted"])]

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

