package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class FinishPage extends BasePage {
    public static final By FINISH_LOCATOR = By.cssSelector(".subheader");
    public static final String ENDPOINT = "checkout-complete.html";
    public static final By PONY_EXPRESS_LOCATOR = By.cssSelector(".pony_express");

    public FinishPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FinishPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(FINISH_LOCATOR));
        } catch (TimeoutException e) {
            Assert.fail("Finish Page is not opened");
        }
        return this;
    }

    @Step("Open finish page")
    @Override
    public FinishPage openPage() {
        driver.get(URL + ENDPOINT);
        return this;
    }

    public boolean isPonyExpressDisplayed() {
        return driver.findElement(PONY_EXPRESS_LOCATOR).isDisplayed();
    }
}
