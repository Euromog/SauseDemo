package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    public static final By CHECKOUT_BUTTON = By.cssSelector("[.checkout_button]");
    public static final By FIRSTNAME_INPUT = By.id("first-name");
    public static final By ZIPCODE_INPUT = By.id("postal-code");
    public static final By LASTNAME_INPUT = By.id("last-name");
    public static final By CONTINUE_BUTTON = By.cssSelector("input[type='submit']");
    public static final By CANCEL_BUTTON = By.cssSelector("*[.cart_cancel_link]");
    public static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");


    public void checkout(String firstName, String lastName, String zipCode) {
        driver.findElement(FIRSTNAME_INPUT).sendKeys(firstName);
        driver.findElement(LASTNAME_INPUT).sendKeys(lastName);
        driver.findElement(ZIPCODE_INPUT).sendKeys(zipCode);
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public void sendKeysToFirstName(String firstName) {
        driver.findElement(FIRSTNAME_INPUT).sendKeys(firstName);
    }

    public void sendKeysToLastName(String lastName) {
        driver.findElement(LASTNAME_INPUT).sendKeys(lastName);
    }

    public void sendKeysToZipCodeName(String zipCode) {
        driver.findElement(ZIPCODE_INPUT).sendKeys(zipCode);
    }

    public void checkoutCancel() {
        driver.findElement(CANCEL_BUTTON).click();
    }

    public void openCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void clickContinue() {
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
