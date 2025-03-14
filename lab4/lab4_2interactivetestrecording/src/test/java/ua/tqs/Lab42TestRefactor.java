package ua.tqs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(io.github.bonigarcia.seljup.SeleniumJupiter.class) // Para gerenciamento automático do WebDriver
public class Lab42TestRefactor {
    private WebDriver driver;
    private JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // WebDriverManager gerencia o ChromeDriver automaticamente
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Tempo de espera reduzido para eficiência
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testBlazeDemoPurchase() {
        driver.get("https://blazedemo.com/");

        // Selecionar voo
        driver.findElement(By.xpath("//input[@value='Find Flights']")).click();
        driver.findElement(By.xpath("//tr[3]/td/input")).click();

        // Preencher formulário de compra
        driver.findElement(By.id("inputName")).sendKeys("Bruno");
        driver.findElement(By.id("address")).sendKeys("123 Main st");
        driver.findElement(By.id("city")).sendKeys("Curitiba");
        driver.findElement(By.id("state")).sendKeys("Parana");
        driver.findElement(By.id("zipCode")).sendKeys("12345");
        driver.findElement(By.id("creditCardNumber")).sendKeys("12312321");
        driver.findElement(By.id("nameOnCard")).sendKeys("bino");

        // Marcar checkbox "Remember Me" e finalizar compra
        driver.findElement(By.id("rememberMe")).click();
        driver.findElement(By.xpath("//input[@value='Purchase Flight']")).click();

        // Verificar se a compra foi bem-sucedida
        assertEquals("BlazeDemo Confirmation", driver.getTitle());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
