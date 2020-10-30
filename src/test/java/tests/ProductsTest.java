package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {

    @BeforeMethod
    public void login() {
        loginPage
                .openPage()
                .isPageOpened()
                .login(USERNAME, PASSWORD);
    }

    @Test(description = "Adding item into cart", retryAnalyzer = RetryAnalyzer.class)
    public void productShouldBeAddedIntoCart() {
        productsPage
                .openPage()
                .isPageOpened()
                .addItemToCart(PRODUCT_NAME);
        String itemNameInTheCart = cartPage
                .openPage()
                .isPageOpened()
                .getItemNameForProduct(PRODUCT_NAME);

        assertEquals(itemNameInTheCart, PRODUCT_NAME, "Sauce Labs Fleece Jacket item is displayed in the cart");
    }

    @Test(description = "Add to cart button is switched to remove button")
    public void addToCartButtonSwitchToRemove() {
        String addToCartButton = productsPage
                .openPage()
                .isPageOpened()
                .addItemToCart(PRODUCT_NAME)
                .getAddToCartRemoveButtonName(PRODUCT_NAME);

        assertEquals(addToCartButton, "REMOVE", "REMOVE button should be displayed");
    }

    @Test(description = "Removing item from cart")
    public void itemShouldBeRemovedFromCart() {
        String actualResult1 = productsPage
                .openPage()
                .isPageOpened()
                .addItemToCart(PRODUCT_NAME)
                .addItemToCart(PRODUCT2_NAME)
                .getShoppingCartCounter();
        String actualResult2 = productsPage.removeItemFromCart(PRODUCT_NAME)
                .getShoppingCartCounter();
        assertEquals(actualResult1, "2", "Number 2 should be displayed near the cart icon");
        assertEquals(actualResult2, "1", "Number 1 should be displayed near the cart icon");
    }
}
