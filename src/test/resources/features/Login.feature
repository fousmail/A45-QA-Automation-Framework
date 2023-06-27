Feature: Login feature
  Scenario: Login Success
    Given I open login page
    When I enter my email "demo@class.com"
    And I enter my password "te$t$tudent"
    And I click submit
    Then I am logged in