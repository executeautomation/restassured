Feature: PostProfile
  Test POST operation using REST-assured library

  @smoke
  Scenario: Verify Post operation for Profile
    Given I Perform POST operation for "/posts/{profileNo}/profile" with body
      | name | profile |
      | Sams | 2       |
    Then I should see the body has name as "Sams"


