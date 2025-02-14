package page;

import base.BasePage;
import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Objects;


public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitForDashboardPage() {
        wait.until(ExpectedConditions.urlToBe(ConfigReader.getProperty("DASHBOARD_URL")));
    }

    public boolean isDashboardDisplayed() {
        return Objects.equals(driver.getCurrentUrl(), ConfigReader.getProperty("DASHBOARD_URL"));
    }

}
