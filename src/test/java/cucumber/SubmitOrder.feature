
@tag
Feature: Purchase the order from Ecommerce site
  I want to use this for my cucumber learning
  
  Background:
  Given I landed on Ecommerce page

@Regression
Scenario Outline: Positive test of submitting order
Given Logged in with username <name> and password <password>
When  I add product <productName> to cart
And  checkout product <productName> and submit the order
Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

   Examples:
      | name             | password    | productName  |
      | Shriya@gmail.com | Shriya@20   | ZARA COAT 3  |
