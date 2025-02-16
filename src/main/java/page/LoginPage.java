package page;

import base.BasePage;
import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;

public class LoginPage extends BasePage {

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(xpath = "//span[@class='p-button-label p-c']")
    WebElement signInButton;

    @FindBy(xpath = "//p[normalize-space()='The password field is required.']")
    WebElement msgPasswordIsRequired;

    @FindBy(xpath = "//p[normalize-space()='The email field is required.']")
    WebElement msgEmailIsRequired;

    @FindBy(xpath = "//a[contains(text(),'Forgot your password?')]")
    WebElement linkForgotYourPassword;

    @FindBy(css = ".text-sm.text-red-600")
    WebElement errMsgEmailOrPassInvalid;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String mail) {
        emailField.sendKeys(mail);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickSignIn() {
        signInButton.click();
    }

    public boolean isPasswordRequiredMessageDisplayed() {
        return (msgPasswordIsRequired).isDisplayed();
    }

    public boolean isEmailRequiredMessageDisplayed() {
        return (msgEmailIsRequired).isDisplayed();
    }

    public String getTextMailIsRequired() {
        return (msgEmailIsRequired).getText();
    }

    public boolean isMsgEmailOrPassErrorDisplayed() {
        return (errMsgEmailOrPassInvalid).isDisplayed();
    }

    public void clickRecoveryPasswordLink() {
        linkForgotYourPassword.click();
    }

    public boolean isLoginPageDisplayed() {
        String expectedUrl = ConfigReader.getProperty("LOGIN_URL");  // Očekivani URL login stranice
        return Objects.equals(driver.getCurrentUrl(), expectedUrl);
    }

    public void login(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
    }


}
