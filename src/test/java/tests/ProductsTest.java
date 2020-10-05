package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {

    @Test
    public void productShouldBeAddedIntoCart() {
        loginPage.openPage();
        loginPage.login(USERNAME, PASSWORD);
        productsPage.addToCart("Sauce Labs Fleece Jacket");
        cartPage.openPage();
        assertEquals(cartPage.getItemNameForProduct("Sauce Labs Fleece Jacket"), "Sauce Labs Fleece Jacket", "Sauce Labs Fleece Jacket item is displayed in the cart");
    }

    @Test
    public void addYoCartButtonSwitchToRemove() {
        loginPage.openPage();
        loginPage.login(USERNAME, PASSWORD);
        assertEquals(productsPage.getAddToCartButton("Sauce Labs Fleece Jacket"), "ADD TO CART", "Add To Cart button should be displayed");
        productsPage.addToCart("Sauce Labs Fleece Jacket");
        assertEquals(productsPage.getAddToCartButton("Sauce Labs Fleece Jacket"), "REMOVE", "REMOVE button should be displayed");
    }
}
