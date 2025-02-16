package cart;

import Base.BaseTest;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;
import page.DashboardPage;
import page.CartComponentPage;

public class TestNegativeScenarioCart extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CartComponentPage cartPage;

    @BeforeMethod
    public void setUp() {
        driver.manage().deleteAllCookies();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        cartPage = new CartComponentPage(driver);
        homePage.clickLoginButton();
        loginPage.login(ConfigReader.getProperty("validEmail"), ConfigReader.getProperty("validPassword"));
    }



    @Test
    public void testCheckoutWithEmptyCart() {
        dashboardPage.waitForLoaderToDisappear();
        dashboardPage.addRandomProductToCart();
        dashboardPage.openCart();
        cartPage.clearCart();
        Assert.assertTrue(cartPage.isEmptyCartMessageDisplayed(), "Message is not Displayed, cart is not empty");
        Assert.assertFalse(cartPage.isCheckoutButtonEnabled(), "Button checkout is not disabled!");
    }
}