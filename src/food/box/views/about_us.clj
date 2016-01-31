(ns food.box.views.about-us
  (:require [food.box.views.application :refer :all]))

(defn about-us
  "Shows the about us web page."
  [conf]
  (layout conf
    [:h1 "About us"]
      
    [:p "Hello to all and welcome to " [:b "ViennaSweety"] "! My wife Margot and me Philipp Ullmann are the founder of this website. We are a young family with three amazingly wonderful children who were all born during a seven year span. Our business is located in " [:a {:href "https://en.wikipedia.org/wiki/Baden_bei_Wien"} "Baden bei Wien"] ", which is a spa town in Austria. Located about 26 km (16 mi) south of " [:a {:href "https://en.wikipedia.org/wiki/Vienna"} "Vienna"] ". ViennaSweety provides a service that will always deliver you traditional Austrian snackes and candies directly to your doorstep!"]
    
    [:p "Vienna, is a true utopia for the sweet-toothed: Candies and treats are made by hand right in the store. Old recipes and traditions, some of which date back to the 18th century. In the center of Vienna tourist can visit a broad range of sweet stores. Famous names like Demel, Manner, Sacher, ... are amoung them. The variousity of their offers can not be explored within a single day. We thought it is a pity that only tourists who visit Vienna are able to enjoy this experience. So the idea was born to generate a suprise box full of candies to our customers all over the world!"]

    [:p "Our store is still very young and small. However, in the spirit of Austrian service and hospitality we believe we can deliver you the best Austrian candy and snacks in terms of quality, quantity, price and delivery speed that will leave you very satisfied."]))
