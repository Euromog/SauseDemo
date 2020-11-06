package tests;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.*;
import steps.ProductSteps;
import tests.base.TestListener;
import utils.CapabilitiesGenerator;

import java.util.concurrent.TimeUnit;

@Log4j2
@Listeners(TestListener.class)

public class BaseTest extends Constants {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutOverviewPage checkoutOverviewPage;
    CheckoutPage checkoutPage;
    FinishPage finishPage;
    protected ProductSteps steps;

    @BeforeMethod
    public void setUp(ITestContext context) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        try {
            driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        } catch (SessionNotCreatedException e) {
            Assert.fail("Браузер не был открыт. Проверьте, что используется корректная версия драйвера");
            log.fatal(e.getLocalizedMessage());
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        createInstances();
        steps = new ProductSteps(driver);
        String variable = "driver";
        System.out.println("Setting driver into context with variable name " + variable);
        context.setAttribute(variable, driver);
    }

    @AfterMethod(alwaysRun = true, description = "Закрытие браузера")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void createInstances() {
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutPage = new CheckoutPage(driver);
        finishPage = new FinishPage(driver);
    }
}
