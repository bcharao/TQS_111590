package ua.tqs;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SeleniumJupiter.class) // Usa Selenium-Jupiter para injeção do WebDriver
class HelloWorldChromeJupiterTest {

    static final Logger log = LoggerFactory.getLogger(HelloWorldChromeJupiterTest.class);

    @BeforeAll
    static void setupClass() {
        // Não há mais necessidade de configurar manualmente o WebDriverManager
    }

    @Test
    void test(WebDriver driver) { // O WebDriver é injetado automaticamente
        // Exercise
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl);
        String title = driver.getTitle();
        log.debug("The title of {} is {}", sutUrl, title);

        // Verify title
        assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java");

        // Navigate to "Slow calculator"
        WebElement slowCalculatorLink = driver.findElement(By.linkText("Slow calculator"));
        slowCalculatorLink.click();

        // Espera para carregamento da página (pode ser substituído por WebDriverWait)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Verify if navigated correctly
        String currentUrl = driver.getCurrentUrl();
        log.debug("Navigated to URL: {}", currentUrl);
        assertThat(currentUrl).contains("slow-calculator");

        // Não há necessidade de chamar driver.quit(), pois Selenium-Jupiter gerencia isso automaticamente
    }
}
