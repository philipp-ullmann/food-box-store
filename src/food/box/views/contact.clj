(ns food.box.views.contact
  (:require [food.box.views.application :refer :all]))

(defn- text-field [[label type id value errors]]
  [(if (id errors) :fieldset.form-group.has-danger :fieldset.form-group)
    [:label.form-control-label {:for id} label]

    [(if (id errors) :input.form-control.form-control-danger :input.form-control)
       {:type     type
        :id       id
        :name     (str "contact[" (name id) "]")
        :value    value
        :required true}]
  
    (errors-for (id errors))])

(defn show
  "Renders the contact formular."
  ([conf] (show {} conf))

  ([{:keys [name email message errors]} conf]
  (layout conf
    [:h1 "Contact"]

    [:form {:method "post" :action "/contact"}

      (map text-field [["Name *"          "text"  :name  name  errors]
                       ["Email Address *" "email" :email email errors]])

      [(if (:message errors) :fieldset.form-group.has-danger :fieldset.form-group)
        [:label.form-control-label {:for "message"} "Message *"]

        [(if (:message errors) :textarea.form-control.form-control-danger :textarea.form-control)
           {:id       "message"
            :name     "contact[message]"
            :required true
            :rows     "8"}
           message]
      
        (errors-for (:message errors))]
    
      [:button.btn.btn-success {:type "submit"} "Send"]
      " | "
      [:a.btn.btn-danger {:href "/"} "Cancel"]])))

(defn created
  "Renders a confirmation message."
  [conf]
  (layout conf
    [:h1 "Thank you for your message!"]
    [:p [:a.btn.btn-warning {:href "/"} "<< Main page"]]))

