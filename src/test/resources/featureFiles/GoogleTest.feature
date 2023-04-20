Feature: To validate google.com
  Background: User visit ebay homePage
    Given user visit ebay homepage https://www.google.com/

  Scenario: Access a Product via category after applying multiple filters
    Given user search keyword test
    Then user should be redirected to search listing page
