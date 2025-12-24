@tag
Feature: Error Validation
I want to use this feature file for my execution
  

@ErrorValidation
Scenario Outline: Error message validation
Given I landed on Ecommerce page
When  Logged in with username <name> and password <password>
Then "Incorrect email or password." message is displayed

   Examples:
      | name             | password    | 
      | Shriya@gmail.com | Shriya@2    | 
