@Login
Feature: Login to dashboard

Scenario: User can login for access the dashboard
    Given user open the website
    When user choose jenis user "cpns"
    And user input email with "test2@gmail.com"
    And user input password with "test1234"
    And click button login
    Then system will be display screen dashboard