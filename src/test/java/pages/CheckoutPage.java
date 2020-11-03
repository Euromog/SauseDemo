package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class CheckoutPage extends BasePage {
    public static final By FIRSTNAME_INPUT = By.id("first-name");
    public static final By ZIPCODE_INPUT = By.id("postal-code");
    public static final By LASTNAME_INPUT = By.id("last-name");
    public static final By CONTINUE_BUTTON = By.cssSelector("input[type='submit']");
    public static final By CANCEL_BUTTON = By.cssSelector(".cart_cancel_link");
    public static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");
    public static final String ENDPOINT = "checkout-step-one.html";

    @Step("Make checkout with {firstName} / {lastName} / {zipCode}")
    public CheckoutOverviewPage checkout(String firstName, String lastName, String zipCode) {
        attemptToCheckout(firstName, lastName, zipCode);
        return new CheckoutOverviewPage(driver);
    }

    @Step("Make checkout with wrong inputs as {firstName} / {lastName} / {zipCode}")
    public CheckoutPage attemptToCheckout(String firstName, String lastName, String zipCode) {
        driver.findElement(FIRSTNAME_INPUT).sendKeys(firstName);
        driver.findElement(LASTNAME_INPUT).sendKeys(lastName);
        driver.findElement(ZIPCODE_INPUT).sendKeys(zipCode);
        driver.findElement(CONTINUE_BUTTON).click();
        return this;
    }

    @Step("Click Cancel button to cancel checkout")
    public CartPage checkoutCancel() {
        driver.findElement(CANCEL_BUTTON).click();
        return new CartPage(driver);
    }

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CONTINUE_BUTTON));
        } catch (TimeoutException e) {
            Assert.fail("Checkout Page is not opened");
        }
        return this;
    }

    @Step("Open Checkout page")
    @Override
    public CheckoutPage openPage() {
        driver.get(URL + ENDPOINT);
        return this;
    }

    @Step("Click continue button")
    public CheckoutOverviewPage clickContinue() {
        driver.findElement(CONTINUE_BUTTON).click();
        return new CheckoutOverviewPage(driver);
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
