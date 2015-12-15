(ns food.box.views.application
  (:require [hiccup.page :refer (html5 include-css)]))

(defn layout
  "Application layout."
  [banner? & body]
  (html5
    [:head
      [:meta {:charset "utf-8"}]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
      [:meta {:name "description" :content "A web store to sell austrian food boxes in different sizes."}]
      [:title "Austrian food box"]
      (include-css "http://yui.yahooapis.com/pure/0.6.0/pure-min.css")
      (include-css "/css/application.css")

      "<!--[if lte IE 8]>"
      (include-css "http://yui.yahooapis.com/pure/0.6.0/grids-responsive-old-ie-min.css")
      "<![endif]-->"

      "<!--[if gt IE 8]><!-->"
      (include-css "http://yui.yahooapis.com/pure/0.6.0/grids-responsive-min.css")
      "<!--<![endif]-->"

      "<!--[if lte IE 8]>"
      (include-css "/css/layout-old-ie.css")
      "<![endif]-->"

      "<!--[if gt IE 8]><!-->"
      (include-css "/css/layout.css")
      "<!--<![endif]-->"]

    [:body
      [:div.pure-menu.pure-menu-horizontal
        [:ul.pure-menu-list
          [:li.pure-menu-item [:a.pure-menu-link {:href "/"}        "TYPES OF BOXES"]]
          [:li.pure-menu-item [:a.pure-menu-link {:href "/contact"} "CONTACT"]]
          [:li.pure-menu-item [:a.pure-menu-link {:href "#"}        "ABOUT US"]]]]

      (if banner? [:div.banner [:hi.banner-head "SIMPLE, UNIQUE" [:br] "AND TASTY!"]])

      body

      [:div.footer.l-box
        [:p
         "Please read the "
         [:a {:href "/terms_and_conditions"} "terms and conditions"]]]]))

(defn error
  "Error dialog box."
  [header body]
  (html5
    [:head
      [:meta {:charset "utf-8"}]
      [:title header]
      (include-css "/css/error.css")]
    [:body
      [:div.dialog
        [:h1 header]]
      [:p body [:br] [:br]
          [:a {:href "/"} "<< Store"]]]))

(defn errors-for
  "Renders all errors for an attribute."
  [errors]
  (when errors (map #(vector :li %) errors)))
