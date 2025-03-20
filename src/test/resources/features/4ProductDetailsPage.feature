Feature: Product Details Page

  @ProductDetailsPageTest
  Scenario: Validate the product title, description, price, images, and availability
    Given User Entered the homepage
    When User logs in with email "shiny.dipty1@gmail.com" and password "vs0faGkMrUKqg?b"
    When User selects the product "Harry Potter and the Cursed Child, Parts One and Two"
    And User applies necessary filters
    And User validates the product details including title, description, price, images, and availability
    And User adds the product to the wishlist
    And User adds the product to the cart
    Then The product is displayed in the cart
