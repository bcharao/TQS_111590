package ua.tqs;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(io.github.bonigarcia.seljup.SeleniumJupiter.class)
public class BookSearchTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // Configuração automática do WebDriver
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testSearchHarryPotter() {
        // Acessar a loja online fictícia
        driver.get("https://cover-bookstore.onrender.com");

        // Encontrar o campo de busca e pesquisar "Harry Potter"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='book-search-input']")));
        searchBox.sendKeys("Harry Potter");
        searchBox.sendKeys(Keys.ENTER);

        // Esperar e verificar se um dos resultados contém o título esperado
        WebElement firstResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".SearchList_bookTitle__1wo4a")));
        assertEquals("Harry Potter and the Sorcerer's Stone", firstResult.getText(), "O livro esperado não foi encontrado!");

        // Verificar se o autor do livro está correto
        WebElement author = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".SearchList_bookAuthor__3giPc")));
        assertEquals("J.K. Rowling", author.getText(), "O autor do livro não está correto!");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}