package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void errorMessageShouldBeDisplayedWithInvalidCredentials() {
        loginPage.openPage();
        loginPage.sendKeysToUserName(USERNAME);
        loginPage.sendKeysToPassword("999");
        loginPage.clickLogin();
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service", "Appropriate error message should be displayed");
    }

    @Test
    public void loginShouldPass() {
        loginPage.openPage();
        loginPage.sendKeysToUserName(USERNAME);
        loginPage.sendKeysToPassword(PASSWORD);
        loginPage.clickLogin();
        assertEquals(loginPage.checkHomePageIsOpen(), "Products", "Home page should be displayed");
    }

    @Test
    public void ErrorMessageShouldBeDisplayedWithEmptyUserName() {
        loginPage.openPage();
        loginPage.clickLogin();
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required", "Username is required error message should be displayed");
    }
}
