(ns food.box.views.contact
  (:require [food.box.views.application :refer :all]))

(defn show
  "Renders the contact formular."
  ([] (show {}))

  ([{:keys [name email message errors]}]
  (layout
    [:div.l-box
      [:h1 "Contact"]

      (if errors
        [:ul.alert-box
          (map #(errors-for (% errors))
               [:name :email :message])])

      [:form.pure-form.pure-form-aligned {:method "post" :action "/contact"}
        [:fieldset
          [:div.pure-control-group
            [:label {:for "name"} "Name *"]
            [:input#name {:type     "text"
                          :name     "contact[name]"
                          :value    name
                          :required true}]]
      
          [:div.pure-control-group
            [:label {:for "email"} "Email Address *"]
            [:input#email {:type     "email"
                           :name     "contact[email]"
                           :value    email
                           :required true}]]
      
          [:div.pure-control-group
            [:label {:for "message"} "Message *"]
            [:textarea#message {:name     "contact[message]"
                                :required true
                                :rows     "8"
                                :cols     "50"}
                               message]]

          [:div.pure-controls
            [:button.pure-button.pure-button-primary {:type "submit"} "Send"]
            " | "
            [:a {:href "/"} "Cancel"]]]]])))

(defn created
  "Renders a confirmation message."
  []
  (layout
    [:p "Thank you for your message!"
        [:a {:href "/"} "Main page"]]))
