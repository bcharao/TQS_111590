package ua.tqs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BlazeDemoPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Elementos da página mapeados com Page Factory
    @FindBy(xpath = "//input[@value='Find Flights']")
    private WebElement findFlightsButton;

    @FindBy(xpath = "//tr[3]/td/input")
    private WebElement chooseFlightButton;

    @FindBy(id = "inputName")
    private WebElement nameInput;

    @FindBy(id = "address")
    private WebElement addressInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "state")
    private WebElement stateInput;

    @FindBy(id = "zipCode")
    private WebElement zipCodeInput;

    @FindBy(id = "creditCardNumber")
    private WebElement creditCardNumberInput;

    @FindBy(id = "nameOnCard")
    private WebElement nameOnCardInput;

    @FindBy(id = "rememberMe")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//input[@value='Purchase Flight']")
    private WebElement purchaseFlightButton;

    // Construtor para inicializar a Page Factory
    public BlazeDemoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Métodos para interagir com a página
    public void searchFlights() {
        wait.until(ExpectedConditions.elementToBeClickable(findFlightsButton)).click();
    }

    public void chooseFlight() {
        wait.until(ExpectedConditions.elementToBeClickable(chooseFlightButton)).click();
    }

    public void fillPassengerDetails(String name, String address, String city, String state, String zip, String cardNumber, String cardName) {
        nameInput.sendKeys(name);
        addressInput.sendKeys(address);
        cityInput.sendKeys(city);
        stateInput.sendKeys(state);
        zipCodeInput.sendKeys(zip);
        creditCardNumberInput.sendKeys(cardNumber);
        nameOnCardInput.sendKeys(cardName);
        rememberMeCheckbox.click();
    }

    public void confirmPurchase() {
        wait.until(ExpectedConditions.elementToBeClickable(purchaseFlightButton)).click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
