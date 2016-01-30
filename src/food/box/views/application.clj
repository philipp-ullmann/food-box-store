(ns food.box.views.application
  (:require [clojure.string :refer [join]]
            [hiccup.page    :refer [html5]]))

(defn- nav-item [name path selected?]
  (if selected?
    [:a.nav-item.nav-link.active {:href path} name [:span.sr-only "(current)"]]
    [:a.nav-item.nav-link {:href path} name]))

(defn layout
  "Application layout."
  [{:keys [nav-types? nav-contact? nav-about?]} & body]
  (html5 {:lang "en"}
    [:head
      [:meta {:charset "utf-8"}]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1, shrink-to-fit=no"}]
      [:meta {:http-equiv "x-ua-compatible" :content "ie=edge"}]
      [:meta {:name "description" :content "Austrian candy and snack food boxes to your doorstep."}]

      [:title "ViennaSweety"]

      [:link {:rel "stylesheet"
              :href "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css"
              :integrity "sha384-y3tfxAZXuh4HwSYylfB+J125MxIs6mR5FOHamPBG064zB+AFeWH94NdvaCBm8qnd"
              :crossorigin "anonymous"}]]
      [:link {:rel "stylesheet" :href "/css/pricing_table.css"}]

    [:body
      [:div.container

        [:nav.navbar.navbar-light.bg-faded
          [:div.nav.navbar-nav
            (nav-item "TYPES OF BOXES" "/"         nav-types?)
            (nav-item "CONTACT"        "/contact"  nav-contact?)
            (nav-item "ABOUT US"       "/about_us" nav-about?)]]

        body]

      [:script {:src "https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"}]
      [:script {:src "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"
                :integrity "sha384-vZ2WRJMwsjRMW/8U7i6PWi6AlO1L79snBrmgiDpgIWJ82z8eA5lenwvxbMV1PAh7"
                :crossorigin "anonymous"}]]))

(defn error
  "Error dialog box."
  [header body]
  (html5 {:lang "en"}
    [:head
      [:meta {:charset "utf-8"}]
      [:title header]
      [:link {:rel "stylesheet" :href "/css/error.css"}]]

    [:body
      [:div.dialog
        [:h1 header]]
      [:p body [:br] [:br]
          [:a {:href "/"} "<< Store"]]]))

(defn errors-for [errors]
  (when errors [:small.text-muted.text-help "[" (join ", " errors) "]"]))
