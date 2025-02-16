package login;

import Base.BaseTest;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.DashboardPage;
import page.HomePage;
import page.LoginPage;

public class TestNegativeScenarioLogin extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;


    @BeforeMethod
    public void setupTest() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);

    }

    @Test
    public void testLoginWithNonRegisteredMail() {
        String nonRegisteredMail = ConfigReader.getProperty("nonRegisteredMail");
        String validPassword = ConfigReader.getProperty("validPassword");

        homePage.clickLoginButton();
        loginPage.enterEmail(nonRegisteredMail);
        loginPage.enterPassword(validPassword);
        loginPage.clickSignIn();

        Assert.assertTrue(loginPage.isMsgEmailOrPassErrorDisplayed(), "Error message should be displayed for incorrect username.");
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Test FAILED: Korisnik nije ostao na login stranici!");
    }

    @Test
    public void testLoginWithInvalidPassword() {
        String validEmail = ConfigReader.getProperty("validEmail");
        String invalidPassword = ConfigReader.getProperty("invalidPassword");
        homePage.clickLoginButton();
        loginPage.enterEmail(validEmail);
        loginPage.enterPassword(invalidPassword);
        loginPage.clickSignIn();

        Assert.assertTrue(loginPage.isMsgEmailOrPassErrorDisplayed(), "Error message should be displayed for incorrect password.");
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Test FAILED: Korisnik nije ostao na login stranici!");
    }

    @Test
    public void testLoginWithEmptyFields() {
        homePage.clickLoginButton();
        loginPage.enterEmail("");
        loginPage.enterPassword("");
        loginPage.clickSignIn();

        //  Proveravamo da li su prikazane obe poruke o grešci
        Assert.assertTrue(loginPage.isEmailRequiredMessageDisplayed(), "Test FAILED: Nema poruke za prazno korisničko ime!");
        Assert.assertTrue(loginPage.isPasswordRequiredMessageDisplayed(), "Test FAILED: Nema poruke za praznu lozinku!");
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Test FAILED: Korisnik nije ostao na login stranici!");
    }

}

