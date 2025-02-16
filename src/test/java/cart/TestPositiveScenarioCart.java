package cart;

import Base.BaseTest;
import config.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.CartComponentPage;
import page.DashboardPage;
import page.HomePage;
import page.LoginPage;

import java.time.Instant;
import java.util.Random;

public class TestPositiveScenarioCart extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CartComponentPage cartPage;


    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        cartPage = new CartComponentPage(driver);
        homePage.clickLoginButton();
        loginPage.login(ConfigReader.getProperty("validEmail"), ConfigReader.getProperty("validPassword"));
    }

    @Test(priority = 2)
    public void testAddProductToCart() {
        dashboardPage.waitForLoaderToDisappear();
        int before = cartPage.getCartItemCount();
        dashboardPage.addRandomProductToCart();
        Assert.assertTrue(dashboardPage.isAddedProductMsgDisplayed());
        int after = cartPage.getCartItemCount();
        Assert.assertEquals(after, before + 1, "Broj proizvoda u korpi nije ažuriran!");
    }


    @Test(priority = 1)
    public void verifyProductNameInCart() {
        dashboardPage.waitForLoaderToDisappear();  // Čekaš da se loader ukloni

        // Dodaj proizvod u korpu i preuzmi naziv proizvoda
        String[] productDetails = dashboardPage.addRandomAvailableProductToCart();
        String productNameDashboard = productDetails[0];  // Naziv proizvoda sa Dashboard stranice

        dashboardPage.openCart();  // Otvori korpu

        String productNameCart = cartPage.getFirstProductName();  // Dohvati naziv proizvoda iz korpe

        System.out.println("Naziv proizvoda na Dashboard-u: " + productNameDashboard);
        System.out.println("Naziv proizvoda u korpi: " + productNameCart);

        // Uporedi samo naziv proizvoda, ignorisanje cene i količine
        Assert.assertEquals(productNameDashboard.trim(), productNameCart.trim(), "Naziv proizvoda se ne poklapa!");
    }






    @Test(priority =3)
        public void testRemoveProductFromCart () {
            dashboardPage.waitForLoaderToDisappear();
            dashboardPage.addRandomProductToCart();
            dashboardPage.openCart();
            int before = cartPage.getCartItemCount();
            cartPage.removeProductFromCart();
            int after = cartPage.getCartItemCount();
            Assert.assertEquals(after, before - 1, "Broj proizvoda u korpi nije ažuriran nakon uklanjanja!");
        }

        @Test(priority=4)
        public void testClearCart () {
            dashboardPage.waitForLoaderToDisappear();
            dashboardPage.addRandomProductToCart();
            Assert.assertTrue(dashboardPage.isAddedProductMsgDisplayed());
            dashboardPage.openCart();
            cartPage.clearCart();
            Assert.assertTrue(cartPage.isEmptyCartMessageDisplayed(), "Korpa nije obrisana!");
        }
    }
