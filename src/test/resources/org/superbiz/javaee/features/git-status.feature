@git_status_endpoint
Feature: Endpoint to get git status

  #GET
  Scenario: Retrieve users list
    When I make a GET call to "/git-status" endpoint
    Then the response status code should be "200"
    And response content type should be "application/json"
    And response should be json:
    """
      {
          "gitMetadata": {
              "branch": "${json-unit.ignore}",
              "buildTime": "${json-unit.ignore}",
              "buildUserEmail": "${json-unit.ignore}",
              "buildUserName": "${json-unit.ignore}",
              "commitId": "${json-unit.ignore}",
              "commitIdAbbrev": "${json-unit.ignore}",
              "commitMessageFull": "${json-unit.ignore}",
              "commitMessageShort": "${json-unit.ignore}",
              "commitTime": "${json-unit.ignore}",
              "commitUserEmail": "${json-unit.ignore}",
              "commitUserName": "${json-unit.ignore}"
          }
      }
    """
