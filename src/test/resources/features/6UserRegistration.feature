Feature: User Registration and Login
@UserRegistrationTest
  Scenario: Automate the user registration process, login, and change password
    Given User on homepage
    When User registers an account
    When User logs in with mobile number "7328878592" and password "Dipti@123"
    When User changes the password to "Dip@123"
    Then displays password changed successfully