package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ProductsPage extends BasePage {
    public final String commonPathForAddRemoveButton = "//*[contains(text(),'%s')]/ancestor::div[@class='inventory_item']";
    public final String addToCartRemoveButtonLocator = commonPathForAddRemoveButton + "//button";
    public final String addToCartLocator = commonPathForAddRemoveButton + "//button[contains(@class, 'btn_primary')]";
    public final String removeLocator = commonPathForAddRemoveButton + "//button[contains(@class, 'btn_secondary')]";

    public static final String ENDPOINT = "inventory.html";
    public static final By PRODUCT_NUMBER_IN_CART_LOCATOR = By.cssSelector(".fa-layers-counter");
    public static final By PRODUCTS_LABEL_LOCATOR = By.cssSelector(".product_label");
    public static final By PRODUCTS_ITEM_LOCATOR = By.className("inventory_item");
    public static final By PRODUCTS_ITEM_PRICE_LOCATOR = By.className("inventory_item_price");
    public static final By PRODUCTS_ITEM_NAME_LOCATOR = By.className("inventory_item_name");
    public static final By CART_BUTTON = By.cssSelector("[data-icon='shopping-cart']");


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProductsPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTS_LABEL_LOCATOR));
        } catch (TimeoutException e) {
            Assert.fail("Products Page is not opened");
        }
        return this;
    }

    @Override
    public ProductsPage openPage() {
        driver.get(URL + ENDPOINT);
        return this;
    }

    public ProductsPage addItemToCart(String productName) {
        driver.findElement(By.xpath(String.format(addToCartLocator, productName))).click();
        return this;
    }

    public ProductsPage removeItemFromCart(String productName) {
        driver.findElement(By.xpath(String.format(removeLocator, productName))).click();
        return this;
    }

    public String getAddToCartRemoveButtonName(String productName) {
        return driver.findElement(By.xpath(String.format(addToCartRemoveButtonLocator, productName))).getText();
    }

    public String getShoppingCartCounter() {
        return driver.findElement(PRODUCT_NUMBER_IN_CART_LOCATOR).getText();
    }

    public CartPage openCartByIcon() {
        driver.findElement(CART_BUTTON).click();
        return new CartPage(driver);
    }

}
