Feature: Search Functionality
@SearchFunctionalityTest
  Scenario: Automate searching for products, filtering results by Discounts, price, and Language 
    Given User is on the homepage
    When User searches for "Harry Potter" book in the search bar
    When User filters the book by discount 21% - 30% in the refine search section
    When User filters the book by price Rs.500 - Rs.1000 in the refine search section
    When User filters the book by language English in the refine search section
    Then Searched filters are displayed
