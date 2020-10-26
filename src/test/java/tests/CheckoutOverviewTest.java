package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutOverviewTest extends BaseTest {

    @Test(description = "Moving to Product page after canceling checkout overview")
    public void moveToProductPageWhenCanceledCheckoutOverview() {
        checkoutOverviewPage
                .openPage()
                .isPageOpened()
                .cancelCheckoutOverview()
                .isPageOpened();
    }

    @Test(description = "Correct checkout info is displayed")
    public void checkoutInfoShouldBeLike() {
        productsPage
                .openPage()
                .isPageOpened()
                .addItemToCart(PRODUCT_NAME)
                .addItemToCart(PRODUCT2_NAME);
        String itemTotalActual = checkoutOverviewPage
                .openPage()
                .isPageOpened()
                .getItemTotal();
        String taxActual = checkoutOverviewPage.getTax();
        String totalActual = checkoutOverviewPage.getTotal();

        assertEquals(itemTotalActual, ITEM_TOTAL_FOR_PRODUCT_PRODUCT2, "Item Total should be " + ITEM_TOTAL_FOR_PRODUCT_PRODUCT2);
        assertEquals(taxActual, TAX_FOR_PRODUCT_PRODUCT2, "Tax should be " + TAX_FOR_PRODUCT_PRODUCT2);
        assertEquals(totalActual, TOTAL_FOR_PRODUCT_PRODUCT2, "Total should be " + TOTAL_FOR_PRODUCT_PRODUCT2);
    }
}
