@ShoppingCartTest
Feature: Shopping cart Page

  Scenario: Automate adding items to the cart, removing them, updating quantities, and proceeding to checkout
    Given User Entered homepage
    When User logged in to the account with "shiny.dipty1@gmail.com" and password "vs0faGkMrUKqg?b"
    When User searches "Harry Potter" and adds products to the cart
    When User removes the product and updates the quantities
    When User proceeds to the checkout
    Then Checkout page is displayed
