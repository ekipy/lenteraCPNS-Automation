@Examination
Feature: Start Examination

Scenario: Ensure examination or try out can be interact and the result will be display
    Given user initiated to login for exam
    And system will be display screen dashboard
    When user click tab paket saya
    And system display list of paket has been purchase
    And user click buka paket on list of paket
    And click mulai ujian
    And click langsung ujian
    And choose option on each question and finish the exam
    Then check the result