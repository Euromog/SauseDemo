package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginPage extends BasePage {
    public static final By USERNAME_INPUT = By.id("user-name");
    public static final By PASSWORD_INPUT = By.id("password");
    public static final By LOGIN_BUTTON = By.id("login-button");
    public static final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");
    public static final String ENDPOINT = "index.html";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoginPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(LOGIN_BUTTON));
        } catch (TimeoutException e) {
            Assert.fail("Страница не загрузилась. Не найдена кнопка по локатору " + LOGIN_BUTTON);
        }
        return this;
    }

    @Step("Open Login Page")
    @Override
    public LoginPage openPage() {
        driver.get(URL + ENDPOINT);
        return this;
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    @Step("Trying to login as {username} / {password}")
    public LoginPage attemptToLogin(String username, String password) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return this;
    }

    @Step("Login as {username} / {password}")
    public ProductsPage login(String username, String password) {
        attemptToLogin(username, password);
        return new ProductsPage(driver);
    }
}


