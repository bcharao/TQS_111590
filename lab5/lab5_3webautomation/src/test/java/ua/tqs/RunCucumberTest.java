package ua.tqs;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@SuiteDisplayName("Cucumber Test Suite")
@SelectClasspathResource("features") // pasta 'features' dentro de resources
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "ua.tqs") // seu package
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
public class RunCucumberTest {
    // classe vazia, o Cucumber/JUnit 5 cuida do resto
}
