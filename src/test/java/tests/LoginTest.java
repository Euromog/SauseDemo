package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(description = "Error message should appear when logging with invalid credentials", dataProvider = "InvalidTestDataForLogin")
    public void errorMessageShouldBeDisplayedWhenLogging(String username, String password, String errorMessage) {
        String actualResult = loginPage
                .openPage()
                .isPageOpened()
                .attemptToLogin(username, password)
                .getErrorMessage();

        assertEquals(actualResult, errorMessage, errorMessage + " error message should be displayed");
    }

    @DataProvider(name = "InvalidTestDataForLogin")
    public Object[][] testDataForLogin() {
        return new Object[][]{
                {"", PASSWORD, "Epic sadface: Username is required"},
                {USERNAME, "", "Epic sadface: Password is required"},
                {WRONG_USERNAME, WRONG_PASSWORD, "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(description = "User should be logged in successful")
    public void isSuccessfulLogin() {
        String successLogin = loginPage
                .openPage()
                .isPageOpened()
                .login(USERNAME, PASSWORD)
                .isPageOpened()
                .getAddToCartRemoveButtonName(PRODUCT_NAME);

        assertEquals(successLogin, "ADD TO CART", "Login should be successful and products page should be opened");
    }
}
