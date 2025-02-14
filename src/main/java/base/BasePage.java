package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

   public WebDriver driver;
    public WebDriverWait wait;

    // Konstruktor sa wait instancom
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Unos teksta u polje (sa čišćenjem starog unosa)
    protected void enterInput(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    // Klik na element
    protected void clickElement(WebElement element) {
        waitForVisibility(element);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    // Provera da li je element vidljiv
    protected boolean isElementDisplayed(WebElement element) {
        try {
            waitForVisibility(element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getElementText(WebElement element) {
        waitForVisibility(element);
        return element.getText();
    }



}






