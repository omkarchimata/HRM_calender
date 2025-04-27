
@holiday
Feature: holiday Feature
  
  Scenario: Add and delete holiday Feature
    Given I open browser
    And open the admin page
    When I enter username and password
    And I click on Login button
    Then validate home page title "OrangeHRM"
    When I click on leave option
    When I click on configure button
    And I select Holiday option
    Then Validate availability of add button
    When I click on Add button
    Then Validate Add Holiday 
    And Enter Holiday Name "Automation Day"
    And Select holiday date "12-6-2027"
    And Validate holiday already added
    And Select half or full
    And select Repeats Annually
    And click on save
    
