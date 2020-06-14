Feature: Account Types

  @wip
  Scenario: Driver User
    Given the user logged as "driver"
    When the user navigates "Activities" "Calender Events"
    Then the title contains "Calender Events - Activities"
