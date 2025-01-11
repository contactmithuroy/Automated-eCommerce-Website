package com.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.pageObject.LogoutPage;
import com.pageObject.LoginPage;
import com.testBase.BaseClass;

public class LogoutTestCase extends BaseClass {
    LogoutPage logoutPage;
    LoginPage loginPage;

    @BeforeClass
    public void setupPageObject() {
        loginPage = new LoginPage(driver);
        logoutPage = new LogoutPage(driver);
    }

    @Parameters("url")
    @Test(priority = 1, description = "Verify that login and logout functionality work successfully")
    public void testLoginAndLogout(String url) {
        // Navigate to Login page
        driver.get(url);

        // Verify Login Page Loads
        String loginPageTitle = loginPage.getPageTitle();
        Assert.assertEquals(loginPageTitle, "Automation Exercise - Signup / Login", "Login page title mismatch.");
        System.out.println("Login page loaded successfully with title: " + loginPageTitle);

        // Perform Login
        boolean isLoggedIn = loginPage.loginWithValidCredentials();
        Assert.assertTrue(isLoggedIn, "Login failed with valid credentials.");
        System.out.println("Successfully logged in.");

        // Perform Logout
        boolean isLoggedOut = logoutPage.logoutSuccessfully();
        Assert.assertTrue(isLoggedOut, "Logout failed.");
        System.out.println("Successfully logged out.");
    }
}
