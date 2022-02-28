Feature: Car Valuation Feature

  @verifyCarData
  Scenario: Read input, get vehicle registration numbers and valuate vehicle and compare the data

    Given I read the input file and extract vehicle registration numbers
    When I read the output file and get the vehicle details
    Then I navigate to cazoo car valuation page and get vehicle data and validate vehicle expected and actual details