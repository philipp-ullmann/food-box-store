(ns food.box.views.order
  (:require [food.box.views.application :refer :all]
            [food.box.models.country    :refer [COUNTRIES]]))

(defn show
  "Renders the order formular."
  [box first-name last-name email city state country terms-accepted?]
  (layout
    [:div.l-box
      [:h1 "Order \"" box "\" box"]

      [:form.pure-form.pure-form-stacked {:method "post" :action "/order"}
        [:fieldset
          [:legend "Address"]
      
          [:div.pure-g
            [:div.pure-u-1.pure-u-md-1-3
              [:label {:for "first-name"} "First Name*"]
              [:input#first-name.pure-u-23-24 {:type "text" :required true :value first-name}]]
      
            [:div.pure-u-1.pure-u-md-1-3
              [:label {:for "last-name"} "Last Name*"]
              [:input#last-name.pure-u-23-24 {:type "text" :required true :value last-name}]]
      
            [:div.pure-u-1.pure-u-md-1-3
              [:label {:for "email"} "E-Mail*"]
              [:input#email.pure-u-23-24 {:type "email" :required true :value email}]]
      
            [:div.pure-u-1.pure-u-md-1-3
              [:label {:for "city"} "City*"]
              [:input#city.pure-u-23-24 {:type "text" :required true :value city}]]

            [:div.pure-u-1.pure-u-md-1-3
              [:label {:for "state"} "State*"]
              [:input#state.pure-u-23-24 {:type "text" :required true :value state}]]
      
            [:div.pure-u-1.pure-u-md-1-3
              [:label {:for "country"} "Country*"]
              [:select#country.pure-input-1-2
                (map #(vector :option %) COUNTRIES)]]]
      
          [:label.pure-checkbox {:for "terms-accepted"}
            [:input#terms-accepted {:type "checkbox" :checked terms-accepted?}
                                   " I've read the terms and conditions"]]

          [:input#box {:type "hidden" :value box}]
      
          [:button.pure-button.pure-button-primary {:type "submit"} "Submit"]]]]))
