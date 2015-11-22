(ns food.box.matcher
  (:require [kerodon.impl   :refer :all]
            [kerodon.test   :refer :all]))

;; HELPER
;; ============================================================================

(defn checkbox-checked
  "Returns the checked value of a checkbox."
  [state selector]
  (-> (:enlive state)
      (form-element-for selector)
      first
      :attrs
      (get :checked)))

(defn option-tags
  "Returns the option tags of a selection list."
  [state selector]
  (-> (form-element-for (:enlive state) selector)
      first
      :content))

(defn option-selected
  "Returns the selected value of an option tag."
  [option-tag]
  (get-in option-tag [:attrs :selected]))

;; CHECKBOX
;; ============================================================================

(defmacro checked? [selector]
  `(validate =
             #(checkbox-checked % ~selector)
             "on"
             (~'checked? ~selector :checked "on")))

(defmacro unchecked? [selector]
  `(validate =
             #(checkbox-checked % ~selector)
             nil
             (~'unchecked? ~selector :checked nil)))

;; SELECT
;; ============================================================================

(defmacro selected? [selector value]
  `(validate =
             #(some (fn [option#]
                      (and (= ~value     (first (:content option#)))
                           (= "selected" (option-selected option#))))

                    (option-tags % ~selector))
             true
             (~'selected? ~selector ~value)))

(defmacro no-selections? [selector]
  `(validate =
             #(every? (fn [option#] (nil? (option-selected option#)))
                      (option-tags % ~selector))
             true
             (~'no-selections? ~selector)))
