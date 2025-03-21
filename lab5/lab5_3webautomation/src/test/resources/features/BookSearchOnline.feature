Feature: Online Book Search
  To verify that customers can search for books in the online library
  using a web interface (Selenium + Cucumber)

  Background:
    Given I am on the bookstore homepage

  @search
  Scenario: Search for "Harry Potter" and find the correct book
    When I search for "Harry Potter"
    Then I should see 1 or more results
    And the first result should have title "Harry Potter and the Sorcerer's Stone"
    And the first result should have author "J.K. Rowling"
