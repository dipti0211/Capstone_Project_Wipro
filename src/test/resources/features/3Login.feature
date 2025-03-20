Feature: Login Functionality

  @LoginTest
  Scenario Outline: Valid Login with Multiple Credentials
    Given User is on the login page
    When User enters email "<email>" and password "<password>"
    And Clicks on login button
    Then User should be logged in successfully

    Examples:
      | email                  | password         |
      | shiny.dipty1@gmail.com | vs0faGkMrUKqg?b  |
      | user2@example.com      | test@123         |
