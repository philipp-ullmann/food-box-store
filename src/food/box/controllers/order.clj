(ns food.box.controllers.order
  (:require [food.box.views.order :as view]
            [bouncer.core         :as b]
            [ring.util.response   :refer [redirect]]
            [compojure.core       :refer [defroutes GET POST]]))

(defn create
  "Validates an order and sends confirmation and notification emails."
  [box first-name last-name email city state country terms-accepted?]
  (let [order {:box             box
               :first-name      first-name
               :last-name       last-name
               :email           email
               :city            city
               :state           state
               :country         country
               :terms-accepted? terms-accepted?}]

    (if (b/valid? order)
      (redirect "/order")

      (view/show box)))) 

(defroutes routes
  (GET "/order" [box] (view/show box))

  (POST "/order"
        [box first-name last-name email city state country terms-accepted]
        (create box first-name last-name email city state country terms-accepted?)))
