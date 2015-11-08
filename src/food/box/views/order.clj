(ns food.box.views.order
  (:require [food.box.views.application :refer :all]))

(defn show
  "Renders the order formular."
  [box]
  (layout
    [:div.l-box
      [:h1 "Order \"" box "\" box"]

      [:form.pure-form.pure-form-stacked {:method "post" :action "/order"}
        [:fieldset
          [:legend "Address"]
      
          [:div.pure-g
            [:div.pure-u-1.pure-u-md-1-3
              [:label {:for "first-name"} "First Name*"]
              [:input#first-name.pure-u-23-24 {:type "text" :required true}]]
      
            [:div.pure-u-1.pure-u-md-1-3
              [:label {:for "last-name"} "Last Name*"]
              [:input#last-name.pure-u-23-24 {:type "text" :required true}]]
      
            [:div.pure-u-1.pure-u-md-1-3
              [:label {:for "email"} "E-Mail*"]
              [:input#email.pure-u-23-24 {:type "email" :required true}]]
      
            [:div.pure-u-1.pure-u-md-1-3
              [:label {:for "city"} "City*"]
              [:input#city.pure-u-23-24 {:type "text" :required true}]]
      
            [:div.pure-u-1.pure-u-md-1-3
              [:label {:for "state"} "State"]
              [:select#state.pure-input-1-2
                [:option "AL"]
                [:option "CA"]
                [:option "IL"]]]]
      
          [:label.pure-checkbox {:for "terms"}
            [:input#terms {:type "checkbox"} " I've read the terms and conditions"]]
      
          [:button.pure-button.pure-button-primary {:type "submit"} "Submit"]]]]))
