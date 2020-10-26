package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckoutTest extends BaseTest {

    @Test(description = "Verifying checkout flow")
    public void checkout() {
        productsPage
                .openPage()
                .isPageOpened()
                .addItemToCart(PRODUCT_NAME)
                .addItemToCart(PRODUCT2_NAME);
        Boolean checkoutResult = cartPage
                .openPage()
                .isPageOpened()
                .clickCheckout()
                .isPageOpened()
                .checkout(FIRST_NAME, LAST_NAME, ZIP_CODE)
                .isPageOpened()
                .finishCheckout()
                .isPageOpened()
                .isPonyExpressDisplayed();
        assertTrue(checkoutResult, "Checkout should be done");
    }

    @DataProvider(name = "TestDataForCheckoutInputs")
    public Object[][] testDataForLogin() {
        return new Object[][]{
                {"", LAST_NAME, ZIP_CODE, "Error: First Name is required"},
                {FIRST_NAME, "", ZIP_CODE, "Error: Last Name is required"},
                {FIRST_NAME, LAST_NAME, "", "Error: Postal Code is required"}
        };
    }

    @Test(description = "Error message should be displayed when entering wrong checkout info", dataProvider = "TestDataForCheckoutInputs", invocationCount = 5, threadPoolSize = 10)
    public void errorMessageShouldBeDisplayedWithWrongCheckoutInfo(String FIRST_NAME, String LAST_NAME, String ZIP_CODE, String errorMessage) {
        String actualResult = checkoutPage
                .openPage()
                .isPageOpened()
                .attemptToCheckout(FIRST_NAME, LAST_NAME, ZIP_CODE)
                .getErrorMessage();

        assertEquals(actualResult, errorMessage, "Appropriate error message should be displayed / Field is required");
    }

    @Test(description = "Moving to Product Page when canceling checkout")
    public void productPageShouldOpenWhenCanceledCheckout() {
        checkoutPage
                .openPage()
                .isPageOpened()
                .checkoutCancel()
                .isPageOpened();
    }
}

