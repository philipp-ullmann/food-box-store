(ns food.box.views.application
  (:require [hiccup.page :refer (html5 include-css)]))

(defn- menu-item [name url selected?]
  (let [classes (if selected? "pure-menu-item pure-menu-selected"
                              "pure-menu-item")]
    [:li {:class classes} [:a.pure-menu-link {:href url} name]]))

(defn layout
  "Application layout."
  [{:keys [banner? menu-types? menu-contact? menu-about?]} & body]
  (html5
    [:head
      [:meta {:charset "utf-8"}]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
      [:meta {:name "description" :content "Austrian candy and snack food boxes to your doorstep."}]
      [:title "ViennaSweety"]
      (include-css "/css/pure-min.css")
      (include-css "/css/application.css")

      "<!--[if lte IE 8]>"
      (include-css "/css/grids-responsive-old-ie-min.css")
      "<![endif]-->"

      "<!--[if gt IE 8]><!-->"
      (include-css "/css/grids-responsive-min.css")
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
          (menu-item "TYPES OF BOXES" "/"         menu-types?)
          (menu-item "CONTACT"        "/contact"  menu-contact?)
          (menu-item "ABOUT US"       "/about_us" menu-about?)]]

      (if banner? [:div.banner [:hi.banner-head "ViennaSweety" [:br] "TASTY FOOD BOX!"]])

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
