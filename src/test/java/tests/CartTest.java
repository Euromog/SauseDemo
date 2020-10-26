package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {
    @Test(description = "Items remain in the cart after continue shopping")
    public void productsPageShouldBeAfterTappingContinueShoppingButton() {
        String shoppingCartCounterInPtoducts = productsPage
                .openPage()
                .isPageOpened()
                .addItemToCart(PRODUCT_NAME)
                .openCartByIcon()
                .isPageOpened()
                .clickContinueShopping()
                .isPageOpened()
                .getShoppingCartCounter();
        productsPage.addItemToCart(PRODUCT2_NAME);
        String shoppingCartCounterInCart = cartPage
                .openPage()
                .isPageOpened()
                .getShoppingCartCounter();
        assertEquals(shoppingCartCounterInPtoducts, "1", "1 item should remain after tapping continue shopping");
        assertEquals(shoppingCartCounterInCart, "2", "2 items should be displayed near the cart icon");
    }

    @Test(description = "Shopping cart counter shows correct number of items in the cart")
    public void shoppingCartCounterShouldShowCorrectNumber() {
        String shoppingCartCounter2 = productsPage
                .openPage()
                .isPageOpened()
                .addItemToCart(PRODUCT2_NAME)
                .addItemToCart(PRODUCT_NAME)
                .getShoppingCartCounter();
        assertEquals(shoppingCartCounter2, "2", "2 items should be displayed near the cart icon");

        String shoppingCartCounter1 = cartPage.openPage()
                .isPageOpened()
                .removeItem(PRODUCT_NAME)
                .getShoppingCartCounter();
        assertEquals(shoppingCartCounter1, "1", "1 items should be displayed near the cart icon");
    }

    @Test(description = "Item information should be correct in the cart")
    public void productInfoShouldBeCorrect() {
        productsPage
                .openPage()
                .isPageOpened()
                .addItemToCart(PRODUCT2_NAME);
        String itemName = cartPage.openPage()
                .isPageOpened()
                .getItemNameForProduct(PRODUCT2_NAME);
        String quantity = cartPage.getQuantityForProduct(PRODUCT2_NAME);
        String price = cartPage.getPriceForProduct(PRODUCT2_NAME);

        assertEquals(itemName, PRODUCT2_NAME, PRODUCT2_NAME + " item is displayed in the cart");
        assertEquals(quantity, PRODUCT2_QUANTITY, PRODUCT2_QUANTITY + " " + PRODUCT2_NAME + " item should be displayed");
        assertEquals(price, PRODUCT2_PRICE, PRODUCT2_NAME + " item should cost " + PRODUCT2_PRICE);
    }

    @Test(description = "Item should be removed from the cart")
    public void itemShouldBeRemovedFromCart() {
        productsPage
                .openPage()
                .isPageOpened()
                .addItemToCart(PRODUCT2_NAME);
        cartPage
                .openPage()
                .isPageOpened()
                .removeItem(PRODUCT2_NAME);
        Integer expectedArraySize = 0;
        assertEquals(cartPage.listOfItemsInCart(), expectedArraySize, expectedArraySize + " items should be displayed in the shopping cart");
    }
}
