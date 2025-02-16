package page;

import base.BasePage;
import config.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class DashboardPage extends BasePage {
    WebDriver driver;
    JavascriptExecutor js;

    @FindBy(css = ".flex.flex-row-reverse")
    public List<WebElement> productList;

    @FindBy(css = "h1")
    public List<WebElement> productNames;

    @FindBy(css = ".font-semibold.ml-3")
    public List<WebElement> productPrices;

    @FindBy(css = ".px-1.ml-auto.p-button.p-component")  // Aktivna "Add to Cart" dugmad
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//button[@class='px-1 ml-auto p-button p-component p-disabled']")  // Disabled dugmad
    private List<WebElement> disabledCartButtons;

    @FindBy(xpath = "//button[contains(@class, 'p-button') and contains(@class, 'p-component')]")
    private WebElement cartCounter;

    @FindBy(css = "button[class*='p-button'][class*='p-component']")
    private WebElement cartMainIcon;

    @FindBy(css = ".Toastify__toast-body")
    private WebElement msgProductAddSuccessfully;

    @FindBy(css = "div[role='status']")  // Loader element
    private WebElement loader;

    public DashboardPage(WebDriver driver) {
        super(driver);
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
//***************METODE*************************

    public boolean waitForDashboardPage() {
        try {
            wait.until(ExpectedConditions.urlToBe(ConfigReader.getProperty("DASHBOARD_URL")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public int getProductCount() {
        return productList.size();
    }

    public void waitForLoaderToDisappear() {
        waitForElementToDisappear(loader);
    }

    public void addProductToCart(int index) {
        waitForLoaderToDisappear();
        // Osiguravamo da proizvod ima aktivno "Add to Cart" dugme
        if (index < 0 || index >= addToCartButtons.size()) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }
        WebElement button = addToCartButtons.get(index);
        if (Objects.requireNonNull(button.getAttribute("class")).contains("p-disabled")) {
            throw new IllegalStateException("Product " + index + " is 'Out of Stock' ");
        }
        js.executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
        wait.until(ExpectedConditions.visibilityOf(msgProductAddSuccessfully));
    }

    public void addRandomProductToCart() {
        waitForLoaderToDisappear();
        waitUntilVisible(productList);

        List<WebElement> availableButtons = addToCartButtons.stream()
                .filter(button -> !button.getAttribute("class").contains("p-disabled")).toList();
        if (availableButtons.isEmpty()) {
            throw new IllegalStateException("There are no available products!");
        }
        WebElement randomButton = availableButtons.get(new Random().nextInt(availableButtons.size()));
        js.executeScript("arguments[0].scrollIntoView(true);", randomButton);
        randomButton.click();
    }

    public void openCart() {
        waitForLoaderToDisappear();
        js.executeScript("arguments[0].scrollIntoView(true);", cartMainIcon);
        waitUntilVisible(cartMainIcon);
        cartMainIcon.click();
    }

    public int getCartItemCount() {
        waitForLoaderToDisappear();
        String countText = cartCounter.getText().trim();
        return countText.isEmpty() ? 0 : Integer.parseInt(countText);
    }

    public boolean isAddedProductMsgDisplayed() {
        return msgProductAddSuccessfully.isDisplayed();
    }

    public String[] addRandomAvailableProductToCart() {
        waitForLoaderToDisappear();
        waitUntilVisible(productList);

        List<WebElement> availableButtons = addToCartButtons.stream()
                .filter(button -> !button.getAttribute("class").contains("p-disabled")).toList();
        if (availableButtons.isEmpty()) {
            throw new IllegalStateException("There are no available products!");
        }
        WebElement randomButton = availableButtons.get(new Random().nextInt(availableButtons.size()));
        int selectedIndex = addToCartButtons.indexOf(randomButton);


        System.out.println("Dodajem proizvod: " + productNames.get(selectedIndex).getText());  // LOG

        js.executeScript("arguments[0].scrollIntoView(true);", randomButton);
        randomButton.click();

        wait.until(ExpectedConditions.visibilityOf(productPrices.get(selectedIndex)));
        String productName = productNames.get(selectedIndex).getText();

        System.out.println("Indeks dugmeta: " + selectedIndex);
        System.out.println("Naziv proizvoda pre klika: " + productNames.get(selectedIndex).getText());
        return new String[]{productName};
    }


    public double parsePrice(String priceText) {
        String cleanedPrice = priceText.replaceAll("[^\\d.,]", "").replace(",", ".");
        if (cleanedPrice.isEmpty()) {
            System.err.println("Price text is empty! Check your locator for productPrices.");
            throw new IllegalStateException("Price text is empty!"); // Bacite iznimku ako je cijena prazna
        }
        return Double.parseDouble(cleanedPrice);


    }

}