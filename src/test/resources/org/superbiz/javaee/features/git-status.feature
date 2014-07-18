@git_status_endpoint
Feature: Endpoint to get git status

  #GET
  Scenario: Retrieve users list
    When I make a GET call to "/git-status" endpoint
    Then the response status code should be "200"
    And response content type should be "application/json"
    And response should be a json with the fields "branch,buildTime,buildUserEmail,buildUserName,commitId,commitIdAbbrev,commitMessageShort,commitMessageFull,commitTime,commitUserEmail,commitUserName,describe"
