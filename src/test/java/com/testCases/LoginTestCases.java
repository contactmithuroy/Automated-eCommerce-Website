package com.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.pageObject.LoginPage;
import com.testBase.BaseClass;

public class LoginTestCases extends BaseClass {
    LoginPage loginPage;

    @BeforeClass
    public void setupPageObject() {
        loginPage = new LoginPage(driver);
    }

    @Parameters("url")
    @Test(description = "Verify that the login page loads successfully")
    public void testLoginPageLoadsSuccessfully(String url) {
        driver.get(url); 
        String title = loginPage.getPageTitle();
        Assert.assertEquals(title, "Automation Exercise - Signup / Login", "Login page title mismatch.");
        System.out.println("Login page loaded successfully with title: " + title);
    }

    @Test(dependsOnMethods = {"testLoginPageLoadsSuccessfully"}, priority = 1, dataProvider = "loginData", dataProviderClass= BaseClass.class, description = "Verify that login is successful with valid credentials")
    public void testLoginWithValidCredentials(String email, String password) {
        boolean isLoggedIn = loginPage.loginWithCredentials(email,password );
        Assert.assertTrue(isLoggedIn, "Login failed with valid credentials.");
        System.out.println("Successfully logged in with valid credentials.");
    }
}
