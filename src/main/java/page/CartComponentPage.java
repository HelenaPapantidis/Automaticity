package page;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartComponentPage extends BasePage {
    WebDriver driver;

    public CartComponentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//div[@class='flex align-middle w-full max-w-lg'])")
    public List<WebElement> cartItems;

    @FindBy(css = ".p-button-icon.p-c.pi.pi-times")
    private WebElement buttonX;

    @FindBy(css = ".p-button-icon.p-c.pi.pi-trash")
    private WebElement clearCartButton;

    @FindBy(css = "div[class*='z-10 text-3xl ']")
    private WebElement emptyCartMessage;

    @FindBy(css = ".text-lg.mb-4")
    public WebElement cartTotal;

    @FindBy(xpath = "//button[contains(@class, 'p-button') and contains(@class, 'p-component')]")
    private WebElement cartCounter;

    @FindBy(css = "button[aria-label='Checkout']")
    private WebElement checkoutButton;

    public boolean isEmptyCartMessageDisplayed() {
        return emptyCartMessage.isDisplayed();
    }

    public void clearCart() {
        clickElement(clearCartButton);
    }

    public void clickCheckoutButton() {
        clickElement(checkoutButton);
    }

    public int getCartItemCount() {
        String countText = cartCounter.getText().trim();
        return countText.isEmpty() ? 0 : Integer.parseInt(countText);
    }

    public void removeProductFromCart() {
        int before = getCartItemCount();
        clickElement(buttonX);
        wait.until(driver -> getCartItemCount() == before - 1);
    }

    public boolean isCheckoutButtonEnabled() {
        return checkoutButton.isEnabled();
    }

    public double parsePrice(String priceText) {
        String cleanedPrice = priceText.replaceAll("[^\\d.,]", "").replace(",", ".");
        if (cleanedPrice.isEmpty()) {
            System.err.println("Price text is empty! Check your locator for cartTotal.");
            throw new IllegalStateException("Price text is empty!");  // Bacite iznimku ako je cijena prazna
        }
        return Double.parseDouble(cleanedPrice);
    }

    public String getFirstProductName() {
        waitUntilVisible(cartItems.get(0));  // Osiguravamo da je prvi proizvod vidljiv
        String productName = getElementText(cartItems.get(0)).split("\n")[0].trim();
        return productName;// Dohvatamo ceo tekst (proveri da li sadrži i cenu!)
    }

    public double getFirstProductPrice() {
        waitUntilVisible(cartTotal);  // Čekamo da se total prikaže
        return parsePrice(cartTotal.getText());
    }

}



