@navigate
Feature: Navigation Menu

  Scenario: Fleet—> Vehicles
    Given the user is on the login page
    And the user enter sales manager information
    When the user navigate to fleet,vehicles
    Then the title should be vehicles

  Scenario: Marketing—> Campaigns
    Given the user is on the login page
    And the user enter sales manager information
    When the user navigate to marketing,campaigns
    Then the title should be campaigns

  Scenario: Activities—> Calendar Events
    Given the user is on the login page
    And the user enter sales manager information
    When the user navigate to activates,calender events
    Then the title should be calender events



