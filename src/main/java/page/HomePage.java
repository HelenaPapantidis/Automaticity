package page;

import base.BasePage;
import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    @FindBy(xpath = "//span[contains(text(),'AQA eShop')]")
      private WebElement pageTitle;

    @FindBy(id = "loginBtn")
      private WebElement loginButton;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    private WebElement registerButton;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    protected  String getTitlePage() {
        return pageTitle.getText();
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickToRegisterButton() {
        registerButton.click();
    }

}
