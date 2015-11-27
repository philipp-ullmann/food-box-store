(ns food.box.models.conf
  (:require [environ.core :refer [env]]))

; PORT
(def PORT (Integer. (:port env)))

; BOXES
(def SMALL   "small")
(def REGULAR "regular")
(def PREMIUM "premium")

(def BOXES [SMALL REGULAR PREMIUM])

; PRICES
(def PRICES
  {SMALL   "$15"
   REGULAR "$25"
   PREMIUM "$35"})

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
  (not= (:environment env) "test"))
