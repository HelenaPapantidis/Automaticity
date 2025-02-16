package Base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import config.ConfigReader;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected JavascriptExecutor js;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String baseURL = ConfigReader.getProperty("baseURL");
        System.out.println(baseURL);
        if (baseURL != null) {
            driver.get(baseURL);
        }
        driver.manage().deleteAllCookies();
    }
    @AfterClass
    public void tearDown() {
            if (driver != null) {
                driver.quit();
            }

        }


   }



