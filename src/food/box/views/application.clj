(ns food.box.views.application
  (:require [hiccup.page :refer (html5 include-css)]))

(defn layout
  "Application layout."
  [& body]
  (html5
    [:head
      [:meta {:charset "utf-8"}]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
      [:meta {:name "description" :content "A web store to sell austrian food boxes in different sizes."}]
      [:title "Austrian food box"]
      (include-css "http://yui.yahooapis.com/pure/0.6.0/pure-min.css")

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
        [:a.pure-menu-heading {:href "#"} "Your Logo"]
        [:ul.pure-menu-list
          [:li.pure-menu-item [:a.pure-menu-link {:href "#"} "Home"]]
          [:li.pure-menu-item.pure-menu-selected [:a.pure-menu-link {:href "#"} "Pricing"]]
          [:li.pure-menu-item [:a.pure-menu-link {:href "#"} "Contact"]]]]

      [:div.banner [:hi.banner-head "Simple Pricing." [:br] "Try before you buy."]]

      body

      [:div.footer.l-box
        [:p
         [:a {:href "#"} "Try now"]
         " for 14 days. No credit card required. Header image courtesy of "
         [:a {:href "http://unsplash.com/"} "Unsplash"]]]]))
