Feature: Basic Arithmetic

  Background: A Calculator
    Given a calculator I just turned on

  Scenario: Addition
    When I add 4 and 5
    Then the result is 9

  Scenario: Substraction
    When I substract 7 to 2
    Then the result is 5

  Scenario: Multiplication
    When I multiply 3 and 4
    Then the result is 12

  Scenario: Division
    When i divide 4 by 2
    Then the result is 2

  Scenario: Division by 0
    When i divide 4 by 0
    Then the result is an error

  Scenario Outline: Several additions
    When I add <a> and <b>
    Then the result is <c>

    Examples: Single digits
      | a | b | c  |
      | 1 | 2 | 3  |
      | 3 | 7 | 10 |

  Scenario Outline: Several substraction
    When I substract <a> and <b>
    Then the result is <c>

    Examples: Single digits
      | a | b | c  |
      | 1 | 2 | -1  |
      | 3 | 7 | -4 |

  Scenario Outline: Several multiplications
    When I multiply <a> and <b>
    Then the result is <c>

    Examples: Single digits
      | a | b | c  |
      | 1 | 2 | 2  |
      | 3 | 7 | 21 |

  Scenario Outline: Several divisions
    When I divide <a> by <b>
    Then the result is <c>

    Examples: Single digits
      | a | b | c  |
      | 4 | 2 | 2  |
      | 6 | 3 | 2  |
