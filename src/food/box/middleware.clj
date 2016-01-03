(ns food.box.middleware
  (:require [taoensso.timbre                 :as log]
            [ring.util.response              :refer [response]]
            [de.bertschneider.clj-geoip.core :as geoip]
            [food.box.views.application      :as view]

            [food.box.models [conf   :refer [EMAIL-ENABLED?]]
                             [mailer :refer [send-exception-notification!]]]))

(defn wrap-location
  "GeoIP ring handler to add location information to the request map."
  [handler]
  (let [lookup-service (geoip/multi-lookup-service)]
    (fn [request]
      (let [ip (or (:x-forwarded-for request)
                   (:remote-addr request))
            location (geoip/lookup lookup-service ip)
            req (assoc request :location location)]
        (handler req)))))

(defn wrap-exception
  "Handles an application exception."
  [handler]
  (fn [req]
    (try (handler req)
      (catch Throwable t
        (log/error t)

        (when EMAIL-ENABLED?
          (send-exception-notification! t))

        (response
          (view/error
            "We're sorry, but something went wrong."
            "We've been notified about this issue and we'll take a look at it shortly."))))))
