/*
 * (C) Copyright 2017 Boni Garcia (https://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package ua.tqs;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculatorSteps {

    static final Logger log = getLogger(lookup().lookupClass());

    private Calculator calc;
    private Exception caughtException;

    @Given("^a calculator I just turned on$")
    public void setup() {
        calc = new Calculator();
        caughtException = null;
    }

    @When("I add {int} and {int}")
    public void add(int arg1, int arg2) {
        log.debug("Adding {} and {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("+");
    }

    @When("I subtract {int} and {int}")
    public void i_subtract_and(int arg1, int arg2) {
        log.debug("Subtracting {} and {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("-");
    }

    @When("I multiply {int} and {int}")
    public void multiply(int arg1, int arg2) {
        log.debug("Multiplying {} and {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("*");
    }

    @When("I divide {int} by {int}")
    public void i_divide_by(int arg1, int arg2) {
        log.debug("Dividing {} by {}", arg1, arg2);

        try {
            calc.push(arg1);
            calc.push(arg2);
            calc.push("/");
        } catch (ArithmeticException e) {
            log.error("Caught exception: {}", e.getMessage());
            caughtException = e; // Store the exception for later verification
        }
    }

    @Then("the result is {double}")
    public void the_result_is(double expected) {
        Number value = calc.value();
        log.debug("Result: {} (expected {})", value, expected);
        assertEquals(expected, value);
    }

    @Then("the result is an error")
    public void the_result_is_an_error() {
        log.debug("Checking if the result is an error");

        // Ensure an exception was actually caught
        assertEquals(ArithmeticException.class, caughtException.getClass(), "Expected an ArithmeticException");
        assertEquals("Division by zero not allowed!", caughtException.getMessage());
    }


}