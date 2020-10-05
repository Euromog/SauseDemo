package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    String addToCartLocator = "//*[contains(text(),'%s')]/ancestor::div[@class='inventory_item']//button";
    public static final By PRODUCTNUMBERINCART = By.cssSelector("*[class='fa-layers-counter shopping_cart_badge']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart(String productName) {
        driver.findElement(By.xpath(String.format(addToCartLocator, productName))).click();
    }

    public String getAddToCartButton(String productName) {
        return driver.findElement(By.xpath(String.format(addToCartLocator, productName))).getText();
    }

    public String numberOfProductsInCart() {
        return driver.findElement(PRODUCTNUMBERINCART).getText();
    }
}
