package e2eTest;

import Base.BaseTest;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.*;
import utils.CSVReader;


public class TestSuccessfulProductPurchase extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CartComponentPage cartPage;
    CheckOutPage checkOutPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        cartPage = new CartComponentPage(driver);
        checkOutPage = new CheckOutPage(driver);
        homePage.clickLoginButton();
        loginPage.login(ConfigReader.getProperty("validEmail"), ConfigReader.getProperty("validPassword"));
    }


    @Test
    public void testCheckoutProcess() throws InterruptedException {

        dashboardPage.waitForLoaderToDisappear();
        int before = cartPage.getCartItemCount();
        dashboardPage.addRandomProductToCart();
        Assert.assertTrue(dashboardPage.isAddedProductMsgDisplayed());
        int after = cartPage.getCartItemCount();
        Assert.assertEquals(after, before + 1, "Broj proizvoda u korpi nije ažuriran!");

        dashboardPage.openCart();
        cartPage.clickCheckoutButton();
        checkOutPage.clickNextStep();
        checkOutPage.clickMakeChanges();
        String[] userData = CSVReader.readCSV("src/test/resources/shippingData.csv");
        checkOutPage.fillShippingInfo(userData[0], userData[1], userData[2], userData[3],
                userData[4], userData[5], userData[6], userData[7]);
        checkOutPage.clickUpdate();
        checkOutPage.clickNextStep();
        // biling info
        checkOutPage.clickMakeChanges();
        Thread.sleep(20000);
        String[] billingData = CSVReader.readCSV("src/test/resources/billingData.csv");
        checkOutPage.fillBillingInfo(billingData[0], billingData[1], billingData[2], billingData[3], billingData[4], billingData[5]);
        checkOutPage.clickUpdate();
        checkOutPage.clickNextStep();
        checkOutPage.finalizePurchase();
    }
}

