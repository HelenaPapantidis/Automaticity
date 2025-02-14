package page;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Collection;

public class RegisterPage extends BasePage {

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = ".p-button-label.p-c")
    private WebElement registerButton;

    @FindBy(xpath = "//p[contains(text(),'username field')]")
    private WebElement msgUserNameIsRequired;

    @FindBy(xpath = "//p[contains(text(),'email field')]")
    private WebElement msgEmailIsRequired;

    @FindBy(xpath = "//p[normalize-space()='The password field is required.']")
    private WebElement msgPasswordIsRequired;

    @FindBy(css = "div.mt-4.text-sm.text-green-600.text-center")
    WebElement msgSuccessfulRegister;

    @FindBy(xpath = "//p[normalize-space()='The password field must be at least 6 characters.']")
    private WebElement msgErrorPasswordField;

    @FindBy(xpath = "//p[normalize-space()='The email has already been taken.']")
    private WebElement msgErrorEmailExist;

    @FindBy(xpath = "//p[normalize-space()='The username has already been taken.']")
    private WebElement msgErrorUserNameExist;

    @FindBy(xpath = "//p[normalize-space()='The email field format is invalid.']")
    private WebElement msgErrorInvalidMail;


    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }



    public boolean isMsgPasswordIsShortDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(msgErrorPasswordField));
        return msgErrorPasswordField.isDisplayed();
    }

    public boolean isMsgMailAlreadyExistIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(msgErrorEmailExist));
        return msgErrorEmailExist.isDisplayed();
    }

    public boolean isMsgUserNameAlreadyExistIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(msgErrorUserNameExist));
        return msgErrorUserNameExist.isDisplayed();
    }


    public void enterUserName(String username) {
        enterInput(usernameField, username);
    }

    public void enterEmail(String email) {
        enterInput(emailField, email);
    }

    public void enterPassword(String password) {
        enterInput(passwordField, password);
    }

    public void clickRegister() {
        clickElement(registerButton);
    }

    public boolean isUsernameRequiredMessageDisplayed() {
        return isElementDisplayed(msgUserNameIsRequired);
    }

    public boolean isEmailRequiredMessageDisplayed() {
        return isElementDisplayed(msgEmailIsRequired);
    }

    public boolean isPasswordRequiredMessageDisplayed() {
        return isElementDisplayed(msgPasswordIsRequired);
    }

    public boolean isSuccessfulRegisterMessageDisplayed() {
        return isElementDisplayed(msgSuccessfulRegister);
    }

    public boolean isPasswordTooShortMessageDisplayed() {
        return isElementDisplayed(msgErrorPasswordField);
    }

    public boolean isEmailAlreadyExistsMessageDisplayed() {
        return isElementDisplayed(msgErrorEmailExist);
    }

    public boolean isUsernameAlreadyExistsMessageDisplayed() {
        return isElementDisplayed(msgErrorUserNameExist);
    }

    public boolean isMsgMailInvalidFormatDisplayed() {
        return isElementDisplayed(msgErrorInvalidMail);
    }

    public String getEmailFormatErrorMessage() {
        return getElementText(msgErrorInvalidMail);
    }

}



