package ua.tqs;
import java.time.Duration;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class Lab42Test {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    JavascriptExecutor js;
    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "");
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        driver.get("https://blazedemo.com/");
        driver.findElement(By.xpath("//input[@value='Find Flights']")).click();
        driver.findElement(By.xpath("//tr[3]/td/input")).click();
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).clear();
        driver.findElement(By.id("inputName")).sendKeys("Bruo");
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).clear();
        driver.findElement(By.id("inputName")).sendKeys("Bruno");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Name'])[1]/following::div[2]")).click();
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).clear();
        driver.findElement(By.id("address")).sendKeys("123 Main st");
        driver.findElement(By.id("city")).clear();
        driver.findElement(By.id("city")).sendKeys("Curitiba");
        driver.findElement(By.xpath("//body")).click();
        driver.findElement(By.id("state")).clear();
        driver.findElement(By.id("state")).sendKeys("Parana");
        driver.findElement(By.id("zipCode")).clear();
        driver.findElement(By.id("zipCode")).sendKeys("12345");
        driver.findElement(By.id("creditCardNumber")).clear();
        driver.findElement(By.id("creditCardNumber")).sendKeys("12312321");
        driver.findElement(By.id("creditCardMonth")).click();
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.id("nameOnCard")).clear();
        driver.findElement(By.id("nameOnCard")).sendKeys("bino");
        driver.findElement(By.id("rememberMe")).click();
        driver.findElement(By.xpath("//input[@value='Purchase Flight']")).click();
        assertEquals("BlazeDemo Confirmation", driver.getTitle());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
