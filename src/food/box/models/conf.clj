(ns food.box.models.conf
  (:require [environ.core   :refer [env]]
            [clojure.string :refer [blank?]]))

; PORT
(def PORT (Integer. (System/getenv "PORT")))

; BOXES
(def SMALL   "small")
(def REGULAR "regular")
(def PREMIUM "premium")

(def BOXES [SMALL REGULAR PREMIUM])

; PRICES
(def PRICES
  {SMALL   "$19"
   REGULAR "$29"
   PREMIUM "$39"})

; BANKING
(def BANK-ACCOUNT
  {:bank    "easybank"
   :owner   "Philipp Ullmann"
   :bic     "EASYATW1"
   :iban    (:iban env)
   :country "Austria"})

; EMAIL
(def GMAIL-CREDENTIALS
  {:email    "ullmann.philipp@gmail.com"
   :password (:mail-password env)})

(def EMAIL-ENABLED?
  (not (blank? (:mail-password env))))
