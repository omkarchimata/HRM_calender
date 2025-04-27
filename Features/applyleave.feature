Feature: Apply leave in Orange HRM

  Scenario: Apply leave with valid date and leave balance
    Given I open browser
    And open the admin page
    When I enter username and password
    And I click on Login button
    Then validate home page title "OrangeHRM"
    When I click on leave option
    When I click on apply button
    Then validate leave types available
    When I select leave type
    Then check availabile leave count
    When I select From date "24-04-2027"
    And I select to date "28-04-2025"
    When I click on apply leave submit button
    And I close browser
    