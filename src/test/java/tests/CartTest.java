package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {
    @Test
    public void productsPageShouldBeAfterTappingContinueShoppingButton() {
        loginPage.openPage();
        loginPage.login(USERNAME, PASSWORD);
        productsPage.addToCart("Sauce Labs Bike Light");
        productsPage.addToCart("Sauce Labs Fleece Jacket");
        cartPage.openCartByIcon();
        cartPage.clickContinueShopping();
        assertEquals(loginPage.checkHomePageIsOpen(), "Products", "Products Page should be open");
    }

    @Test
    public void numberOfProductShouldBeEquals() {
        loginPage.openPage();
        loginPage.login(USERNAME, PASSWORD);
        productsPage.addToCart("Sauce Labs Bike Light");
        productsPage.addToCart("Sauce Labs Fleece Jacket");
        assertEquals(productsPage.numberOfProductsInCart(), "2", "2 items should be displayed on the cart icon");
        cartPage.openPage();
        cartPage.removeItem("Sauce Labs Fleece Jacket");
        cartPage.clickContinueShopping();
        assertEquals(productsPage.numberOfProductsInCart(), "1", "1 items should be displayed on the cart icon after tapping continue shopping button");
    }

    @Test
    public void productInfoShouldBeCorrect() {
        loginPage.openPage();
        loginPage.login(USERNAME, PASSWORD);
        productsPage.addToCart("Sauce Labs Bike Light");
        productsPage.addToCart("Sauce Labs Fleece Jacket");
        cartPage.openPage();
        assertEquals(cartPage.getItemNameForProduct("Sauce Labs Bike Light"), "Sauce Labs Bike Light", "Sauce Labs Bike Light item is displayed in the cart");
        assertEquals(cartPage.getQuantityForProduct("Sauce Labs Bike Light"), "1", "1 Sauce Labs Bike Light item should be displayed");
        assertEquals(cartPage.getPriceForProduct("Sauce Labs Bike Light"), "9.99", "Sauce Labs Bike Light item should cost 9.99");
    }
}
