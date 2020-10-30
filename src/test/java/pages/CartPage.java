package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class CartPage extends BasePage {
    private String endpoint = "cart.html";

    String priceLocator = "//*[contains(text(),'%s')]/ancestor::*[@class='cart_item']//div[@class='inventory_item_price']";
    String quantityLocator = "//*[contains(text(),'%s')]/ancestor::*[@class='cart_item']//div[@class='cart_quantity']";
    String removeItemButton = "//*[contains(text(),'%s')]/ancestor::*[@class='cart_item']//button";
    String itemNameLocator = "//*[contains(text(),'%s')]";
    public static final By CONTINUE_BUTTON = By.xpath("//*[text()='Continue Shopping']");
    public static final By CHECKOUT_BUTTON = By.cssSelector(".checkout_button");
    public static final By PRODUCT_NUMBER_IN_CART_LOCATOR = By.cssSelector("*[class='fa-layers-counter shopping_cart_badge']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CartPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_BUTTON));
        } catch (TimeoutException e) {
            Assert.fail("Cart page is not opened");
        }
        return this;
    }

    @Override
    public CartPage openPage() {
        driver.get(URL + endpoint);
        return this;
    }

    public CartPage removeItem(String productName) {
        driver.findElement(By.xpath(String.format(removeItemButton, productName))).click();
        return this;
    }

    public String getPriceForProduct(String productName) {
        return driver.findElement(By.xpath(String.format(priceLocator, productName))).getText();
    }

    public String getQuantityForProduct(String productName) {
        return driver.findElement(By.xpath(String.format(quantityLocator, productName))).getText();
    }

    public String getItemNameForProduct(String productName) {
        return driver.findElement(By.xpath(String.format(itemNameLocator, productName))).getText();
    }

    public CheckoutPage clickCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
        return new CheckoutPage(driver);
    }

    public ProductsPage clickContinueShopping() {
        driver.findElement(CONTINUE_BUTTON).click();
        return new ProductsPage(driver);
    }

    public String getShoppingCartCounter() {
        return driver.findElement(PRODUCT_NUMBER_IN_CART_LOCATOR).getText();
    }

    public Integer listOfItemsInCart() {
        List elements = driver.findElements(By.cssSelector(".cart_item_label"));
        return elements.size();
    }
}
