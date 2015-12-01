(ns food.box.models.utils
  (:require [bouncer.core :as b]

            (clj-time [core   :as t]
                      [format :as tf])))

(defn assoc-errors
  "Associates the error map."
  [model validator]
  (->> (b/validate model validator)
       second
       ::b/errors
       (assoc model :errors)))

(defn now
  "Returns the current time for the timezone Europe/Vienna."
  []
  (tf/unparse (tf/with-zone (tf/formatter "yyyy-MM-dd HH:mm:ss")
                            (t/time-zone-for-id "Europe/Vienna"))
              (t/now)))
