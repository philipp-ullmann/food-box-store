(ns food.box.matcher
  (:require [kerodon.impl :refer :all]
            [kerodon.test :refer :all]))

;; HELPER
;; ============================================================================

(defn get-form-element
  "Returns the form element for the given selector."
  [state selector]
  (first (form-element-for (:enlive state) selector)))

(defn checkbox-checked
  "Returns the checked value of a checkbox."
  [state selector]
  (get (:attrs (get-form-element state selector))
       :checked))

(defn option-tags
  "Returns the option tags of a selection list."
  [state selector]
  (:content (get-form-element state selector)))

(defn option-selected
  "Returns the selected value of an option tag."
  [option-tag]
  (get-in option-tag [:attrs :selected]))

;; TEXTAREA
;; ============================================================================

(defmacro content? [selector content]
  `(validate =
             #(field->value (get-form-element % ~selector))
             ~content
             (~'content? ~selector ~content)))

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
