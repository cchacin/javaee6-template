@users_endpoint
Feature: REST API to manage users

  Background:
    Given I have the only following rows in the "users" table:
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
                    "id": "${json-unit.ignore}",
                    "created": "${json-unit.ignore}",
                    "modified": "${json-unit.ignore}",
                    "email": "cchacin@superbiz.org",
                    "fullname": "Carlos",
                    "password": "passw0rd",
                    "version": 0
                }
            ]
        }
      """
