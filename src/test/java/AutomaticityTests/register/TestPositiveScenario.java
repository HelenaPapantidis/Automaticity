package AutomaticityTests.register;

import Base.BaseTest;
import com.github.javafaker.Faker;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;
import page.RegisterPage;
import config.ConfigReader;

public class TestPositiveScenario extends BaseTest {
    private WebDriverWait wait;
    private HomePage homePage;
    private RegisterPage registerPage;
    private String randomUsername;
    private String randomEmail;
    private String randomPassword;

    @BeforeMethod
    public void setUpTest() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Faker faker = new Faker();
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        randomUsername = faker.name().username();
        randomEmail = faker.internet().emailAddress();
        randomPassword = faker.internet().password(8, 16);
    }

    @Test
    public void testSuccessfulRegistration() {
        homePage.clickToRegisterButton();
        registerPage.enterUserName(randomUsername);
        registerPage.enterEmail(randomEmail);
        registerPage.enterPassword(randomPassword);
        registerPage.clickRegister();

        // Proveri da li se prikazuje poruka o uspešnoj registraciji
        Assert.assertTrue(registerPage.isSuccessfulRegisterMessageDisplayed(), "Success message should be displayed.");

        // Proveri da li je korisnik prebačen na dashboard
        String expectedUrl= ConfigReader.getProperty("DASHBOARD_URL");
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "User should be redirected to the dashboard after successful registration.");

        // Proveri da li korisnik vidi logout dugme (ili neki indikator prijave)
       // Assert.assertTrue(registerPage.isLogoutButtonDisplayed(), "Logout button should be visible after successful registration.");
    }
}

