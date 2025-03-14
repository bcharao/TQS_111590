package ua.tqs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class BlazeDemoTest {
    private WebDriver driver;
    private BlazeDemoPage blazeDemoPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().driverVersion("133.0.6943.141").setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Inicializar a Page Object
        blazeDemoPage = new BlazeDemoPage(driver);
        driver.get("https://blazedemo.com/");
    }

    @Test
    public void testFlightBooking() {
        // Passo 1: Pesquisar voos
        blazeDemoPage.searchFlights();

        // Passo 2: Escolher um voo
        blazeDemoPage.chooseFlight();

        // Passo 3: Preencher os detalhes do passageiro
        blazeDemoPage.fillPassengerDetails("Bruno", "123 Main st", "Curitiba", "Paraná", "12345", "12312321", "Bino");

        // Passo 4: Confirmar a compra
        blazeDemoPage.confirmPurchase();

        // Verificar se a página de confirmação está correta
        assertEquals("BlazeDemo Confirmation", blazeDemoPage.getPageTitle(), "A página de confirmação não apareceu corretamente!");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}