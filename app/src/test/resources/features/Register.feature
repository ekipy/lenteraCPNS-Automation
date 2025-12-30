Feature: Register Page
    User should be register account with valid data

Scenario: Register with valid data
    Given user open website
    When user click button register
    Then system will be display message validation in "nama" field
    When user fill "nama" field with "Test"
    And click button register
    Then system will be display message validation in "email" field
    When user fill "email" field with "RANDOM"
    And click button register
    Then system will be display message validation in "whatsapp" field
    When user fill "whatsapp" field with "089666111222"
    And click button register
    Then system will be display message validation in "password" field
    When user fill "password" field with "12345678"
    And click button register
    Then system will be display message validation in "konfirmasi" field
    When user fill "konfirmasi" field with "12345678"
    And click button register
    Then system will be display screen dashboard

@Registervalidation
Scenario Outline: Check validation email on Register Page
    Given user open website
    When user click button register
    And user fill "nama" field with "Testing1"
    And user fill "email" field with "<email>"
    And user fill "whatsapp" field with "089554212231"
    And user fill "password" field with "test1234"
    And user fill "konfirmasi" field with "test1234"
    And click button register
    Then system will be display "<message>" validation for field email

    Examples:
    |email              |message                                        |
    |test@gmail         |The email field format is invalid.             |
    |test@kompe.com     |The email field must be a valid email address. |