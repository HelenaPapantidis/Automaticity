package page;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(xpath = "//span[@class='p-button-label p-c']")
    WebElement signInButton;

    @FindBy(xpath = "//span[contains(text(),'Username is required')]")
    WebElement msgUserNameIsRequired;

    @FindBy(xpath = "//span[contains(text(),'Email is required')]")
    WebElement msgEmailIsRequired;

    @FindBy(xpath = "//span[contains(text(),'Invalid email format')]")
    WebElement emailInvalidMessage;

    @FindBy(xpath = "//span[contains(text(),'Incorrect password')]")
    WebElement incorrectInputPasswordMessage;

    @FindBy(xpath = "//a[contains(text(),'Forgot your password?')]")
    WebElement linkForgotYourPassword;

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
 public void clickSignIn (){
     signInButton.click();
 }
    public boolean isUsernameRequiredMessageDisplayed() {
        return (msgUserNameIsRequired).isDisplayed();
    }

    public boolean isEmailRequiredMessageDisplayed() {
        return (msgEmailIsRequired).isDisplayed();
    }

    public String getTextUserNameIsRequired() {
        return (msgUserNameIsRequired).getText();
    }

    public String getTextMailIsRequired() {
        return (msgEmailIsRequired).getText();
    }

    public String getTextInvalidMailInput() {
        return (emailInvalidMessage).getText();
    }

    public String getTextIncorrectPassword() {
        return (incorrectInputPasswordMessage).getText();
    }

    public void clickRecoveryPasswordLink() {
        linkForgotYourPassword.click();
    }
}

