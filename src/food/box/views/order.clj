(ns food.box.views.order
  (:require [clojure.string             :refer [blank?]]
            [food.box.views.application :refer :all]
            [food.box.models.country    :refer [COUNTRIES]]
            [food.box.models.conf       :refer [BANK-ACCOUNT]]))

(defn- country-options [current]
  (map #(vector :option {:value (first %) :selected (if (= current (first %)) "selected")} (second %))
       COUNTRIES))

(defn- text-field [[label type id value errors]]
  [(if (id errors) :fieldset.form-group.has-danger :fieldset.form-group)
    [:label.form-control-label {:for id} label]

    [(if (id errors) :input.form-control.form-control-danger :input.form-control)
       {:type     type
        :id       id
        :name     (str "order[" (name id) "]")
        :value    value
        :required true}]
  
  (errors-for (id errors))])

(defn show
  [{:keys [box first-name last-name email street postcode city country terms-accepted errors]} conf]
  (layout conf 
    (if (:box errors)
      [:div.alert.alert-danger {:role "alert"} (errors-for (:box errors))])

    [:h1 "Order a \"" box "\" box"]

    [:form {:method "post" :action "/order"}
      [:input#box {:type  "hidden"
                   :name  "order[box]"
                   :value box}]

      (map text-field [["First Name *"     "text"  :first-name first-name errors]
                       ["Last Name *"      "text"  :last-name  last-name  errors]
                       ["Email Address *"  "email" :email      email      errors]
                       ["Street *"         "text"  :street     street     errors]
                       ["Postcode / Zip *" "text"  :postcode   postcode   errors]
                       ["Town / City *"    "text"  :city       city       errors]])
    
      [(if (:country errors) :fieldset.form-group.has-danger :fieldset.form-group)
        [:label.form-control-label {:for "country"} "Country *"]

        [(if (:country errors) :select.form-control.form-control-danger :select.form-control)
          {:id   "country"
           :name "order[country]"}
          (country-options country)]

        (errors-for (:country errors))]

      [(if (:terms-accepted errors) :div.checkbox.has-danger :div.checkbox)
        [:label.form-control-label {:for "terms-accepted"}
          [:input#terms-accepted {:type    "checkbox"
                                  :name    "order[terms-accepted]"
                                  :checked terms-accepted}

                                 " I've read the "
                                 [:a {:href   "/terms_and_conditions"
                                      :target "_blank"}
                                     "terms and conditions"]]]

        (errors-for (:terms-accepted errors))]

        [:button.btn.btn-success {:type "submit"} "Submit"]
        " | "
        [:a.btn.btn-danger {:href "/"} "Cancel"]]))

(defn summary-partial
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

(defn create [order conf]
  (layout conf
    (summary-partial order)

    [:p "An order confirmation has been sent to: "
        [:strong (:email order)]]
    [:p [:a.btn.btn-warning {:href "/"} "<< Main page"]
        " | "
        [:a.btn.btn-primary {:href "javascript:window.print()"} "Print"]]))
