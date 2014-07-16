@users_endpoint
Feature: REST API to manage users

  Background:
    Given I have the following users in the database:
      | email             | fullname | password |
      | cchacin@gmail.com | Carlos   | passw0rd |

  #GET
  Scenario: Retrieve users list
    When I make a GET call to "/users" endpoint
    Then the response status code should be "200"
    And response content type should be "application/json"
    And response should be json:
    """
      {
          [
              {
                  "created": "2014-07-11T03:21:11.924-04:00",
                  "email": "cchacin@gmail.com",
                  "fullname": "Carlos",
                  "password": "passw0rd"
              }
          ]
      }
    """
