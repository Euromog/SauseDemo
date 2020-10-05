package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private String endpoint = "cart.html";

    String priceLocator = "//*[contains(text(),'%s')]/ancestor::*[@class='cart_item']//div[@class='inventory_item_price']";
    String quantityLocator = "//*[contains(text(),'%s')]/ancestor::*[@class='cart_item']//div[@class='cart_quantity']";
    String removeItemButton = "//*[contains(text(),'%s')]/ancestor::*[@class='cart_item']//button";
    String itemNameLocator = "//*[contains(text(),'%s')]";

    public static final By CONTINUE_BUTTON = By.xpath("//*[text()='Continue Shopping']");
    public static final By CHECKOUT_BUTTON = By.cssSelector("[.checkout_button]");
    public static final By CART_BUTTON = By.cssSelector("[data-icon='shopping-cart']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void removeItem(String productName) {
        driver.findElement(By.xpath(String.format(removeItemButton, productName))).click();
    }

    public void openPage() {
        driver.get(URL + endpoint);
    }

    public void openCartByIcon() {
        driver.findElement(CART_BUTTON).click();
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

    public void clickCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public void clickContinueShopping() {
        driver.findElement(CONTINUE_BUTTON).click();
    }
}
