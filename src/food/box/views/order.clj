(ns food.box.views.order
  (:require [food.box.views.application :refer :all]
            [food.box.models.country    :refer [COUNTRIES]]))

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
      
            [:button.pure-button.pure-button-primary {:type "submit"} "Confirm"]]]]]))
