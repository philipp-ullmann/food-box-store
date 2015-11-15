(ns food.box.views.order
  (:require [food.box.views.application :refer :all]
            [food.box.models.country    :refer [COUNTRIES]]))

(defn- country-options
  "Returns the option tags for all available countries."
  [current]
  (map #(vector :option {:selected (= current %)} %)
       COUNTRIES))

(defn show
  "Renders the order formular."
  [{:keys [box first-name last-name email street postcode city country terms-accepted]}]
  (layout
    [:div.l-box
      [:h1 "Order \"" box "\" box"]

      [:form.pure-form.pure-form-stacked {:method "post" :action "/order"}
        [:fieldset
          [:legend "Address"]
      
          [:div.pure-g
            [:div.pure-u-1.pure-u-md-1-3
              [:label {:for "first-name"} "First Name *"]
              [:input#first-name.pure-u-23-24 {:type     "text"
                                               :name     "order[first-name]"
                                               :value    first-name
                                               :required true}]]
      
            [:div.pure-u-1.pure-u-md-1-3
              [:label {:for "last-name"} "Last Name *"]
              [:input#last-name.pure-u-23-24 {:type     "text"
                                              :name     "order[last-name]"
                                              :value    last-name
                                              :required true}]]
      
            [:div.pure-u-1.pure-u-md-1-3
              [:label {:for "email"} "Email Address *"]
              [:input#email.pure-u-23-24 {:type     "email"
                                          :name     "order[email]"
                                          :value    email
                                          :required true}]]
      
            [:div.pure-u-1.pure-u-md-1-3
              [:label {:for "street"} "Street *"]
              [:input#street.pure-u-23-24 {:type     "text"
                                           :name     "order[street]"
                                           :value    street
                                           :required true}]]

            [:div.pure-u-1.pure-u-md-1-3
              [:label {:for "postcode"} "Postcode / Zip *"]
              [:input#postcode.pure-u-23-24 {:type     "text"
                                             :name     "order[postcode]"
                                             :value    postcode
                                             :required true}]]

            [:div.pure-u-1.pure-u-md-1-3
              [:label {:for "city"} "Town / City *"]
              [:input#city.pure-u-23-24 {:type     "text"
                                         :name     "order[city]"
                                         :value    city
                                         :required true}]]

            [:div.pure-u-1.pure-u-md-1-3
              [:label {:for "country"} "Country *"]
              [:select#country.pure-input-1-2 {:name "order[country]"}
                                              (country-options country)]]]
      
          [:label.pure-checkbox {:for "terms-accepted"}
            [:input#terms-accepted {:type    "checkbox"
                                    :name    "order[terms-accepted]"
                                    :checked terms-accepted}
                                   " I've read the terms and conditions"]]

          [:input#box {:type  "hidden"
                       :name  "order[box]"
                       :value box}]
      
          [:button.pure-button.pure-button-primary {:type "submit"} "Confirm"]]]]))
