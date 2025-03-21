package ua.tqs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class BookSearchSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // Tempo máximo de 10s para encontrar elementos
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Espera explícita
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on the bookstore homepage")
    public void iAmOnTheBookstoreHomepage() {
        driver.get("https://cover-bookstore.onrender.com");
        // ou sua "fake" online library, se preferir
    }

    @When("I search for {string}")
    public void iSearchFor(String searchText) {
        // Localiza o campo de busca
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-testid='book-search-input']")
        ));
        searchBox.clear();
        searchBox.sendKeys(searchText);
        searchBox.sendKeys(Keys.ENTER);
    }

    @Then("I should see {int} or more results")
    public void iShouldSeeAtLeastNResults(int minimumCount) {
        // Aguardar aparecer ao menos 1 item
        var results = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("[data-testid='book-search-item']"))
        );
        assertTrue(results.size() >= minimumCount,
                "Era esperado ao menos " + minimumCount + " resultado(s), mas retornou: " + results.size());
    }

    @Then("I should see {int} results")
    public void iShouldSeeExactlyNResults(int exactCount) {
        // Aguardar aparecerem resultados (ou até 0 se não tiver)
        var results = driver.findElements(By.cssSelector("[data-testid='book-search-item']"));
        assertEquals(exactCount, results.size(),
                "Quantidade de resultados diferente do esperado!");
    }

    @Then("the first result should have title {string}")
    public void theFirstResultShouldHaveTitle(String expectedTitle) {
        WebElement firstTitle = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("[data-testid='book-search-item'] .SearchList_bookTitle__1wo4a")
        ));
        assertEquals(expectedTitle, firstTitle.getText().trim(),
                "O título do primeiro resultado não corresponde ao esperado!");
    }

    @Then("the first result should have author {string}")
    public void theFirstResultShouldHaveAuthor(String expectedAuthor) {
        WebElement firstAuthor = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-testid='book-search-item'] .SearchList_bookAuthor__3giPc")
        ));
        assertEquals(expectedAuthor, firstAuthor.getText(),
                "O autor do primeiro resultado não corresponde ao esperado!");
    }

}
