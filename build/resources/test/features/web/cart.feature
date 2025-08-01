@web @cart
Feature: Cart Functionality

  @positive
  Scenario: Add item to cart
    Given User is logged in with valid credentials
    When User adds "Sauce Labs Backpack" to the cart
    And User navigates to the cart page
    Then "Sauce Labs Backpack" should be in the cart

  @positive
  Scenario: Remove item from cart
    Given User is logged in with valid credentials
    And User adds "Sauce Labs Bike Light" to the cart
    When User navigates to the cart page
    And User removes "Sauce Labs Bike Light" from the cart
    Then "Sauce Labs Bike Light" should not be in the cart