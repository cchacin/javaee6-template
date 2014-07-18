@users_endpoint
Feature: REST API to manage users

  Background:
    Given I have the following users in the database:
      | id | created             | modified            | email                | fullname | password | version |
      | 1  | 2014-07-16 00:00:00 | 2014-07-16 00:00:00 | cchacin@superbiz.org | Carlos   | passw0rd | 0       |

  #GET
  Scenario: Retrieve users list
    When I make a GET call to "/users" endpoint
    Then the response status code should be "200"
    And response content type should be "application/json"
    And response should be json:
    """
        {
            "users": [
                {
                    "id": 1,
                    "created": "2014-07-17T00:00:00-04:00",
                    "email": "cchacin@superbiz.org",
                    "fullname": "Carlos",
                    "password": "passw0rd",
                    "version": 0
                }
            ]
        }
      """
