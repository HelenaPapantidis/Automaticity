package register;

import Base.BaseTest;
import com.github.javafaker.Faker;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.HomePage;
import page.RegisterPage;
import config.ConfigReader;
import testData.TestDataProvider;

public class TestNegativeScenario extends BaseTest {
    WebDriverWait wait;
    Faker faker;
    HomePage homePage;
    RegisterPage registerPage;
    SoftAssert softAssert;

    @BeforeMethod
    public void setUpTest() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        faker = new Faker();
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        softAssert = new SoftAssert();
        homePage.clickToRegisterButton();
    }

    @AfterMethod
    public void tearDownTest() {
        driver.quit();
    }


    @Test
    public void testRegistrationWithShortPassword() {
        String randomUsername = faker.name().username();
        String shortPassword = "12345";
        String randomEmail = faker.internet().emailAddress();

        registerPage.enterUserName(randomUsername);
        registerPage.enterEmail(randomEmail);
        registerPage.enterPassword(shortPassword);
        registerPage.clickRegister();

        softAssert.assertTrue(registerPage.isMsgPasswordIsShortDisplayed(), "Validacija za kratku lozinku nije prikazana!");
        softAssert.assertEquals(driver.getCurrentUrl(), ConfigReader.getProperty("registerURL"), "Korisnik je preusmeren, a nije trebalo!");

        softAssert.assertAll();
    }

    @Test
    public void testRegistrationWithExistingEmail() {
        String randomUsername = faker.name().username();
        String randomPassword = faker.internet().password(8, 12);
        String existingEmail = ConfigReader.getProperty("registeredMail");

        registerPage.enterUserName(randomUsername);
        registerPage.enterEmail(existingEmail);
        registerPage.enterPassword(randomPassword);
        registerPage.clickRegister();

        softAssert.assertTrue(registerPage.isMsgMailAlreadyExistIsDisplayed(), "Validacija za zauzet email nije prikazana!");
        softAssert.assertEquals(driver.getCurrentUrl(), ConfigReader.getProperty("registerURL"), "Korisnik je preusmeren, a nije trebalo!");

        softAssert.assertAll();
    }

    @Test
    public void testRegistrationWithExistingUsername() {
        String randomPassword = faker.internet().password(8, 12);
        String randomEmail = faker.internet().emailAddress();
        String existingUsername = ConfigReader.getProperty("existingUser");

        registerPage.enterUserName(existingUsername);
        registerPage.enterEmail(randomEmail);
        registerPage.enterPassword(randomPassword);
        registerPage.clickRegister();

        softAssert.assertTrue(registerPage.isMsgUserNameAlreadyExistIsDisplayed(), "Validacija za zauzet username nije prikazana!");
        softAssert.assertEquals(driver.getCurrentUrl(), ConfigReader.getProperty("registerURL"), "Korisnik je preusmeren, a nije trebalo!");

        softAssert.assertAll();
    }

    @Test(dataProvider = "wrongEmailFormat", dataProviderClass = TestDataProvider.class)
    public void testRegistrationWithWrongFormatEmail(String invalidEmail) {
        String randomUsername = faker.name().username();
        String randomPassword = faker.internet().password(8, 16);

        registerPage.enterUserName(randomUsername);
        registerPage.enterEmail(invalidEmail);
        registerPage.enterPassword(randomPassword);
        registerPage.clickRegister();

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = ConfigReader.getProperty("registerURL");

        System.out.println("Expected URL: " + expectedUrl);
        System.out.println("Actual URL: " + actualUrl);

        Assert.assertTrue(registerPage.isMsgMailInvalidFormatDisplayed(), "Error message is not dysplayed ");
        Assert.assertTrue(registerPage.getEmailFormatErrorMessage().contains("format is invalid."), "Error message text is not as expect ");
        Assert.assertEquals(actualUrl, expectedUrl);

        String expectedErrorMessage = "The email field format is invalid.";
        Assert.assertEquals(registerPage.getEmailFormatErrorMessage(), expectedErrorMessage,
                "The error message displayed is not as expected.");

    }

    @Test
    public void testRegistrationWithEmptyFields() {

        registerPage.enterUserName("");
        registerPage.enterEmail("");
        registerPage.enterPassword("");
        registerPage.clickRegister();

        softAssert.assertTrue(registerPage.isUsernameRequiredMessageDisplayed(), "Validacija za username nije prikazana!");
        softAssert.assertTrue(registerPage.isEmailRequiredMessageDisplayed(), "Validacija za email nije prikazana!");
        softAssert.assertTrue(registerPage.isPasswordRequiredMessageDisplayed(), "Validacija za lozinku nije prikazana!");
        softAssert.assertEquals(registerPage.getCurrentUrl(), ConfigReader.getProperty("registerURL"), "Korisnik je preusmeren, a nije trebalo!");

        softAssert.assertAll();


    }

}