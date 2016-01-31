(ns food.box.views.terms
  (:require [food.box.views.application :refer :all]))

(defn terms
  "Shows the terms and conditions web page."
  [conf]
  (layout [conf]
    [:h1 "Terms and Conditions"]
      
    [:p "By using or accessing this website, which is owned and operated by Philipp Ullmann, you acknowledge that you have read, understood, and agree to the following Terms of Service. If, at any time, you do not agree to these Terms, please do not use this Site. As a condition of your use of this Site, you agree that you are at least 18 years of age or are visiting the Site under the supervision of an adult or guardian and that you possess the authority to enter into a binding legal agreement."]
    
    [:h2 "Purchases and deliveries"]

    [:p "We endeavour to deliver within the allotted times however do not guarantee delivery on time. We are not liable for any mis-deliveries due to incorrect addresses being provided. We reserve the right to accept or refuse orders for any reason. Failure to pay for produce does not necesarily mean your delivery will be cancelled. You must cancel your delivery and not rely on non-payment as a form of cancellation."]

    [:h2 "Cancellation policy"]

    [:p "Due to the perishable nature of our products we are unable to accept cancellations or make amendments on delivery day. However if you change your mind about your purchases we can amend your order up until 5pm the day before delivery."]

    [:h2 "Refunds"]

    [:p "Due to the perishable nature of produce, if you are unhappy with any of our products please notify us within 1 day of delivery, so we can come to the best resolution for you. If the issue cannot be resolved and we can't deliver a replacement item we will credit or refund the payment for the unsatisfactory item(s)."]
    
    [:h2 "Warranty Disclaimer"]
    
    [:p "Except as otherwise specifically provided, the site and the products offered on the site are provided on an “as is” and “as available” basis and without warranties of any kind, whether express or implied. To the fullest extent permissible pursuant to applicable law, we disclaim all warranties, express or implied, including, but not limited to, implied warranties of merchantability and fitness for a particular purpose and non-infringement. We do not represent or warrant that the functions contained on the site will be uninterrupted or error-free, that the defects will be corrected, or that the site or the server that makes the site available are free of viruses or other harmful components. We do not make any warrantees or representations regarding the use of the materials on the site in terms of their correctness, accuracy, adequacy, usefulness, timeliness, reliability or otherwise. We have no liability on injury or damage caused by products within the box. Please consume the goods at your own risk. Applicable law may not allow limitations or exclusions on warranties, so the above limitations may not apply to you."]
    
    [:h2 "Privacy"]
   
    [:p "You are entirely responsible for maintaining the confidentiality of your personal details at all times, including any personal account details. You agree to notify us immediately of any discretion or unauthorized use of your personal details."])) 
