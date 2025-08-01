@web @checkout
Feature: Checkout End-to-End Flow

  @endtoend
  Scenario: End-to-end checkout
    Given User is logged in with valid credentials
    And User adds "Sauce Labs Bolt T-Shirt" to the cart
    When User navigates to the cart page
    And User clicks the checkout button
    And User enters first name "John", last name "Doe", and postal code "12345"
    And User clicks the continue button
    And User clicks the finish button
    Then User should see the confirmation message "Thank you for your order!"