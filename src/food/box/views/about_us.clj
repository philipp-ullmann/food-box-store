(ns food.box.views.about-us
  (:require [food.box.views.application :refer :all]))

(defn about-us
  "Shows the about us web page."
  [conf]
  (layout [conf]
    [:div.l-box
      [:h1 "About us"]
      
      [:p "Hello to all and welcome to " [:b "Austrian Alps Tasty Food Box"] "! My wife Margot and me Philipp Ullmann are the founder of this website. Our business is located in " [:a {:href "https://en.wikipedia.org/wiki/Baden_bei_Wien"} "Baden bei Wien"] ", which is a spa town in Austria. Located about 26 km (16 mi) south of " [:a {:href "https://en.wikipedia.org/wiki/Vienna"} "Vienna"] ". We provide a service that will always deliver you traditional Austrian snackes and candies directly to your doorstep!"]]))
