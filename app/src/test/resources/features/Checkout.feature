@Checkout
Feature: Checkout Process

Scenario: User choose product and product will be successfully add to purchase process
    Given user initiated to login
    And system will be display screen dashboard
    When click product "Try Out SKD CPNS 2026 - BATCH 1"
    And product details will be display
    And user choose to checkout product
    And user will be display total amount
    And user will see invoice page
    When user click button Bayar Sekarang
    And user will be see payment method
    And user click payment with QR
    Then QR successfully display

