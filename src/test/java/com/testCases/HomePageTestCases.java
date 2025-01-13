package com.testCases;

import com.testBase.BaseClass;
import com.pageObject.HomePage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HomePageTestCases extends BaseClass {
    HomePage homePage;

    @Parameters("url")
    @Test(groups = {"smoke", "regression"}, priority = 1, description = "Verify that the homepage loads successfully")
    public void testHomePageLoadsSuccessfully(String url) {
        driver.get(url); // Accessing the driver initialized in BaseClass
        homePage = new HomePage(driver);
        String title = homePage.getPageTitle(driver);
        Assert.assertNotNull(title, "Homepage failed to load.");
        System.out.println("Homepage loaded successfully with title: " + title);
    }

    @Test(groups = {"smoke", "regression"}, priority = 2, description = "Validate that navigation links redirect to the correct pages")
    public void testNavigationLinks() {
        homePage.clickProducts(driver);
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/products", "Products link navigation failed.");
        driver.navigate().back();
        
        homePage.clickCart(driver);
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/view_cart", "Cart link navigation failed.");
        driver.navigate().back();
        
        homePage.clickSignupLogin(driver);
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login", "Signup/Login link navigation failed.");
        driver.navigate().back();
        
        homePage.clickContactUs(driver);
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/contact_us", "Contact Us link navigation failed.");
        driver.navigate().back();
        
    }

    @Test(groups = {"smoke", "regression"}, priority = 3, description = "Verify the title of the page")
    public void testPageTitle() {
        String title = homePage.getPageTitle(driver);
        Assert.assertEquals(title, "Automation Exercise", "Page title is incorrect.");
    }

    @Test(groups = {"smoke", "regression"}, priority = 4, description = "Verify the logo of the page")
    public void testLogoVisibility() {
        Assert.assertTrue(homePage.isLogoVisible(driver), "Logo is not visible on the homepage.");
    }

    @Test(groups = {"smoke", "regression"}, priority = 5, description = "Verify the carousel/banner content changes correctly")
    public void testCarouselContent() {
        Assert.assertTrue(homePage.isCarouselContentChanging(driver), "Carousel content is not changing as expected.");
    }

    @Test(groups = {"smoke", "regression"}, priority = 6, description = "Check the visibility and functionality of footer links")
    public void testFooterLinksVisibility() {
        Assert.assertTrue(homePage.areFooterLinksVisible(driver), "Not all footer links are visible.");
    }
}
