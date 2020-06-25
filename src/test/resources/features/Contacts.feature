Feature: Contacts page


  Scenario: Default page number
    Given the user is on the login page
    And the user enter the driver information
    When the user navigates "Customers" "Contacts"
    Then default page number should be 1

    #homework
  Scenario: login as a driver
    Given the user is on the login page
    And the user enter as "store manager "
    When the user should be able to login
    Then the title contains "Dashboard"

  Scenario: Menu Options
    Given the user logged as "driver"
    Then the user should see following options
      | Fleet      |
      | Customers  |
      | Activities |
      | System     |


  Scenario: login as a given user
    Given the user is on the login page
    When the user logs in using following credentials
      | username  | user1       |
      | password  | UserUser123 |
      | firstname | John        |
      | lastname  | Doe         |
    Then the user should be able to login

  Scenario: login as a given user
    Given the user is on the login page
    When the user logs in using following credentials
      | username  | salesmanager101 |
      | password  | UserUser123     |
      | firstname | Peyton          |
      | lastname  | Herber          |
    Then the user should be able to login


  Scenario Outline: login as a given user <userType>
    Given the user is on the login page
    When the user logs in using following credentials
      | username  | <userType>  |
      | password  | UserUser123 |
      | firstname | <firstName> |
      | lastname  | <lastName>  |
    Then the user should be able to login

    Examples:

      | userType        | firstName | lastName |
      | user1           | John      | Doe      |
      | salesmanager101 | Peyton    | Harber   |
      | storemanager58  | Nola      | Hammes   |

  @db
  Scenario: Contacts test with email
    Given the user logged as "store manager"
    And the user navigates "Customers" "Contacts"
    When the user clicks "<email>" from contacts
    Then the information should be same with database

  @wip @db
  Scenario: Contacts test with email
    Given the user logged as "store manager"
    And the user navigates "Customers" "Contacts"
    When the user clicks "mike.jorden@hotmail.com" from contacts
    Then the information "mike.jorden@hotmail.com" should be same with database

  @wip @db
  Scenario Outline: Contacts test with email
    Given the user logged as "store manager"
    And the user navigates "Customers" "Contacts"
    When the user clicks "<email>" from contacts
    Then the information "<email>" should be same with database

    Examples:
      | email                    |
      | mbrackstone9@example.com |
      | mike.jorden@hotmail.com  |
      | Asan@gmail.com           |

