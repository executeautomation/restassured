Feature: ComplexDataGet
  Verify complex data


#  @smoke
#  Scenario: Verify GET operation for complex data
#    Given I perform authentication operation for "/auth/login" with body
#      | email              | password  |
#      | karthik@email.com | haha123 |
#    And I perform GET operation with path parameter for address "/address/"
#      | id |
#      | 1      |
#    Then I should see the street name as "1st street"

  @smoke
  Scenario: Verify GET operation for complex data
    Given I perform authentication operation for "/auth/login" with body
      | email              | password  |
      | karthik@email.com | haha123 |
    And I perform GET operation with path parameter for address "/location/"
      | id |
      | 1      |
    Then I should see the street name as "1st street"
