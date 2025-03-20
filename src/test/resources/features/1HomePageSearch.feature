Feature: HomePage Search Functionality
@SearchTest
  Scenario: User searches for a product
    Given User is on the Bookswagon homepage
    When User searches for a book "Harry Potter" in the search bar
    Then Search results are displayed
#
#@ProductDetailsTest
  #Scenario: Validate Product Details
    #Given User is on the Bookswagon homepage
    #When User searches for "Java Book"
    #And User selects the first product
    #Then Product title, description, price, image, and availability should be displayed
#
#@WishlistTest
  #Scenario: Add Product to Wishlist
    #Given User is logged in
    #When User searches for "Java Book"
    #And User adds the product to the wishlist
    #Then Product should be added to the wishlist successfully
#
#@CartTest
  #Scenario: Add, Remove, Update, and Checkout from Cart
    #Given User is logged in
    #When User adds "Java Book" to the cart
    #And User updates the quantity to "2"
    #And User removes the product from the cart
    #Then The cart should be empty
    #When User adds "Java Book" again to the cart
    #And User proceeds to checkout
    #Then Checkout page should be displayed
