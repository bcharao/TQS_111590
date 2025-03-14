package ua.tqs;

import io.github.bonigarcia.seljup.BrowserType;
import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SeleniumJupiter.class)
public class DockerBrowserTest {

    @Test
    public void testWithDockerChrome(@DockerBrowser(type = BrowserType.EDGE) WebDriver driver) {
        // Abre a página de teste no navegador rodando no Docker
        driver.get("https://blazedemo.com/");

        // Verifica se a página carregou corretamente
        assertEquals("BlazeDemo", driver.getTitle(), "O título da página não é o esperado!");
    }
}
