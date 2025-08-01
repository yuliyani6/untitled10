@web @login
Feature: Login Functionality

  @positive
  Scenario: Login with valid credentials
    Given User is on the Saucedemo login page
    When User enters username "standard_user" and password "secret_sauce"
    And User clicks the login button
    Then User should be redirected to the inventory page

  @negative
  Scenario: Login with invalid password
    Given User is on the Saucedemo login page
    When User enters username "standard_user" and password "wrong_password"
    And User clicks the login button
    Then An error message should be displayed