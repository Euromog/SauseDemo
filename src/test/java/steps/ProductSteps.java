package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.ProductsPage;

//@Log4j2
public class ProductSteps {
    private ProductsPage page;

    public ProductSteps(WebDriver driver) {
        page = new ProductsPage(driver);
    }

    @Step("Adding {productName} to cart")
    public ProductSteps addItemToCart(String productName) {

//        log.info(String.format("Adding '%s' to cart", productName));
        page
                .openPage()
                .isPageOpened()
                .addItemToCart(productName);
        return this;

    }

}

