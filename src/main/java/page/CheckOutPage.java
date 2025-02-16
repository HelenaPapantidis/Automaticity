package page;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutPage extends BasePage {


    public CheckOutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    // ---------- SHIPPING INFORMATION ----------

    @FindBy(css = "#first_name")
    private WebElement firstNameField;

    @FindBy(css = "#last_name")
    private WebElement lastNameField;

    @FindBy(css = "#email")
    private WebElement emailField;

    @FindBy(css = "#phone_number")
    private WebElement phoneField;

    @FindBy(css = "#street_and_number")
    private WebElement streetField;

    @FindBy(css = "#city")
    private WebElement cityField;

    @FindBy(css = "#postal_code")
    private WebElement postalCodeField;

    @FindBy(css = "#country")
    private WebElement countryField;

    @FindBy(css = "button[aria-label='Make changes']")
    private WebElement makeChangesButton;

    @FindBy(css = "button[aria-label='Update']")
    private WebElement makeUpdateButton;

    @FindBy(xpath = "//span[normalize-space()='Next step']")
    private WebElement nextStepButton;

    @FindBy(xpath = "div[role='alert']")
    private WebElement shippinginfoUpdatedMsg;


    // Metoda za popunjavanje forme
    public void fillShippingInfo(String firstName, String lastName, String email, String phone,
                                 String street, String city, String postalCode, String country) {
        // Čekanje da svi elementi budu vidljivi pre nego što počnemo sa unosom
        waitUntilVisible(firstNameField);
        waitUntilVisible(lastNameField);
        waitUntilVisible(emailField);
        waitUntilVisible(phoneField);
        waitUntilVisible(streetField);
        waitUntilVisible(cityField);
        waitUntilVisible(postalCodeField);
        waitUntilVisible(countryField);
        System.out.println("Fields are all visible");
        // Uklanjanje disabled atributa samo ako je potrebno
         js.executeScript("arguments[0].removeAttribute('disabled')", firstNameField);

       // Unos podataka u polja
        clearAndType(firstNameField, firstName);
        clearAndType(lastNameField, lastName);
        clearAndType(emailField, email);
        clearAndType(phoneField, phone);
        clearAndType(streetField, street);
        clearAndType(cityField, city);
        clearAndType(postalCodeField, postalCode);
        clearAndType(countryField, country);

        // Ispis u logu
        System.out.println("Shipping data are succeffully entered");
    }

    public void clickNextStep() {
        waitUntilVisible(nextStepButton);
        js.executeScript("arguments[0].click();", nextStepButton);
    }

    public void clickMakeChanges() {
        System.out.println("Clicking on 'Make Changes'...");
        try {
            waitUntilClickable(makeChangesButton);
            makeChangesButton.click();
            System.out.println("'Make Changes' is clicked");
        } catch (Exception e) {
            System.out.println("make changes is not clicable");
            js.executeScript("arguments[0].click();", makeChangesButton);
        }

    }

    public void clickUpdate() {
        System.out.println("Clicking on Update button...");
        waitUntilVisible(makeUpdateButton);
        js.executeScript("arguments[0].click();", makeUpdateButton);

    }
    public boolean shippingInfoUpdated() {
        waitUntilVisible(shippinginfoUpdatedMsg);
        js.executeScript("arguments[0].scrollIntoView(true);", shippinginfoUpdatedMsg);
         return  shippinginfoUpdatedMsg.isDisplayed();
    }

    // ---------- BILLING INFORMATION ----------


    @FindBy(css = "#cardholder")
    private WebElement cardholderNameField;

    @FindBy(css = "#card_number")
    private WebElement cardNumberField;

    @FindBy(css = "#cvv")
    private WebElement cvvField;

    @FindBy(css = "#card_type")
    private WebElement cardTypeDropdown;

    @FindBy(css = "div[aria-label='Month']")
    private WebElement expirationMonthDropdown;

    @FindBy(css = "#card_expiration_year")
    private WebElement expirationYearDropdown;

    @FindBy(xpath = "//span[normalize-space()='Next step']")
    private WebElement billingNextStepButton;

    public void fillBillingInfo(String cardholderName, String cardType, String cardNumber, String cvv, String expMonth, String expYear) {

        js.executeScript("arguments[0].removeAttribute('disabled')", cardholderNameField);
        js.executeScript("arguments[0].scrollIntoView(true);", cardholderNameField);
        js.executeScript("arguments[0].click();", cardholderNameField);
        System.out.println("Field cardholder is clicked");
        clearAndType(cardholderNameField, cardholderName);
        System.out.println("cardholder field is populated");
        js.executeScript("arguments[0].removeAttribute('aria-hidden');", cardTypeDropdown);
        selectCartType(cardTypeDropdown, cardType);
        clearAndType(cardNumberField, cardNumber);
        clearAndType(cvvField, cvv);
        js.executeScript("arguments[0].removeAttribute('aria-hidden')", expirationMonthDropdown);  // Ukloni 'aria-hidden' atribut
        js.executeScript("arguments[0].removeAttribute('disabled')", expirationMonthDropdown);  // Ukloni 'disabled' atribut
        selectFromMonthDropdown(expirationMonthDropdown, expMonth);
        js.executeScript("arguments[0].removeAttribute('aria-hidden')", expirationYearDropdown);  // Ukloni 'aria-hidden' atribut
        js.executeScript("arguments[0].removeAttribute('disabled')", expirationYearDropdown);
        selectFromYearDropdown(expirationYearDropdown, expYear);
        System.out.println("Billing Data are entered ");

    }

    protected void selectCartType(WebElement dropdown, String optionText) {
        clickElement(dropdown);
        WebElement option = dropdown.findElement(By.xpath("//li[@role='option' and @aria-label='" + optionText + "']"));
        clickElement(option);
    }

    protected void selectFromMonthDropdown(WebElement dropdown, String expMonth) {
        clickElement(dropdown); // Klikni da otvori dropdown

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[@role='option' and @aria-label='" + expMonth + "']") // Ispravan XPath
        ));

        option.click();
    }


    protected void selectFromYearDropdown(WebElement dropdown, String expYear) {
        clickElement(dropdown); // Klikni da otvori dropdown

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[@role='option' and @aria-label='" + expYear + "']") // Ispravan XPath
        ));

        option.click(); // Klikni na pronađenu opciju
    }




    // ---------- FINALIZE PURCHASE ----------
    @FindBy(css = "button[aria-label='Place your order!'] span[class='p-button-label p-c']")
    private WebElement placeYourOrderButton;

    public void finalizePurchase() {
        placeYourOrderButton.click();
    }

}


