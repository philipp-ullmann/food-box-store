(ns food.box.middleware
  (:require [taoensso.timbre            :as log]
            [ring.util.response         :refer [response]]
            [food.box.models.mailer     :refer [send-exception-notification!]]
            [food.box.views.application :as view]))

(defn wrap-exception
  "Handles an application exception."
  [handler]
  (fn [req]
    (try (handler req)
      (catch Throwable t
        (log/error t)
        (send-exception-notification! t)
        (response
          (view/error
            "We're sorry, but something went wrong."
            "We've been notified about this issue and we'll take a look at it shortly."))))))
