package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public static final By USERNAME_INPUT = By.id("user-name");
    public static final By PASSWORD_INPUT = By.id("password");
    public static final By LOGIN_BUTTON = By.id("login-button");
    public static final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");
    public static final By HOMEPAGE_ELEMENT = By.cssSelector(".product_label");

    String endpoint = "index.html";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(URL + endpoint);
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public void login(String username, String password) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public void clickLogin() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    public void sendKeysToUserName(String username) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
    }

    public void sendKeysToPassword(String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    public String checkHomePageIsOpen() {
        return driver.findElement(HOMEPAGE_ELEMENT).getText();
    }
}



