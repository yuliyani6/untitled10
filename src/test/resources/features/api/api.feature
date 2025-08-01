@api
Feature: Dummy API
  @api
  Scenario: Create a new user
    Given I have user details with first name "Yuliyani", last name "Aprilyani", and a unique email
    When I send a request to create the user
    Then the user should be created successfully


  @api
  Scenario: Get user by ID
    Given I have the saved user ID
    When I send a GET request to "/user/{id}"
    Then the response status should be 200
    And the response should contain the user details

  @api
  Scenario: Update the created user
    Given I have a valid user ID
    When I send a PUT request to update the user with:
      | firstName | Michael                    |
      | lastName  | Douglas                    |
      | email     | yuliyani1753791450408@example.com |
    Then the response status code should be 200
    And the response should contain the updated user details

  @api
  Scenario: Delete user
    Given I have the saved user ID
    When I send a DELETE request to "/user/{id}"
    Then the response status should be 200
    And the user should no longer exist