Feature: Rockets
  Scenario: Get all rockets
    Given that the user has entered the correct URI
    When the user sends GET request
    Then the response code should be 200
