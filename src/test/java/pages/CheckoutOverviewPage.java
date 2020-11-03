package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class CheckoutOverviewPage extends BasePage {
    public static final String ENDPOINT = "checkout-step-two.html";
    public static final By ITEM_TOTAL_LOCATOR = By.cssSelector(".summary_subtotal_label");
    public static final By TAX_LOCATOR = By.cssSelector(".summary_tax_label");
    public static final By TOTAL_LOCATOR = By.cssSelector(".summary_total_label");
    public static final By FINISH_BUTTON = By.cssSelector(".cart_button");
    public static final By CANCEL_BUTTON = By.cssSelector(".cart_cancel_link");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutOverviewPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(FINISH_BUTTON));
        } catch (TimeoutException e) {
            Assert.fail("Checkout Overview Page is not opened");
        }
        return this;
    }

    @Step("Open CheckoutOverview page")
    @Override
    public CheckoutOverviewPage openPage() {
        driver.get(URL + ENDPOINT);
        return this;
    }

    public String getItemTotal() {
        return driver.findElement(ITEM_TOTAL_LOCATOR).getText();
    }

    public String getTax() {
        return driver.findElement(TAX_LOCATOR).getText();
    }

    public String getTotal() {
        return driver.findElement(TOTAL_LOCATOR).getText();
    }

    @Step("Click finish button to complete checkout")
    public FinishPage finishCheckout() {
        driver.findElement(FINISH_BUTTON).click();
        return new FinishPage(driver);
    }

    @Step("Click cancel button to cancel checkout")
    public ProductsPage cancelCheckoutOverview() {
        driver.findElement(CANCEL_BUTTON).click();
        return new ProductsPage(driver);
    }
}
