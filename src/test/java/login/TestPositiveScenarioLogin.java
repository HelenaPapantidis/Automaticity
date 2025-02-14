package login;

import Base.BaseTest;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.DashboardPage;
import page.HomePage;
import page.LoginPage;

public class TestPositiveScenarioLogin extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    String validUsername = ConfigReader.getProperty("valid.username");
    String validPassword = ConfigReader.getProperty("valid.password");


    @BeforeMethod
    public void setupTest() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Test
    public void testValidLogin() {
        String validEmail = ConfigReader.getProperty("validEmail");
        String validPassword = ConfigReader.getProperty("validPassword");

        homePage.clickLoginButton();
        loginPage.enterEmail(validEmail);
        loginPage.enterPassword(validPassword);
        loginPage.clickSignIn();
        dashboardPage.waitForDashboardPage();

        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Test FAILED: Dashboard nije prikazan!");
    }


}