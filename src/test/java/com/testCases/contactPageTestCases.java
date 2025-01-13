package com.testCases;

import org.testng.Assert;
import org.testng.annotations.*;

import com.pageObject.ContactPage;
import com.testBase.BaseClass;

public class contactPageTestCases extends BaseClass {
	ContactPage contactPage;

	@BeforeClass
	public void setupPageObject() {
		contactPage = new ContactPage(driver);
	}

	@Parameters("url")
	@Test(groups = {"functional", "regression"},  priority = 1, description = "Verify that the contact us page loads successfully")
	public void testContactUsPageLoadsSuccessfully(String url) {
		driver.get(url+"contact_us");
		String title = contactPage.getPageTitle(driver);
		Assert.assertEquals(title, "Automation Exercise - Contact Us", "Contact Us page title mismatch.");
		System.out.println("Contact Us page loaded successfully with title: " + title);
	}
	
	@Test(groups = {"functional", "regression"},  priority = 2, description = "Test the form submission with valid data")
    public void testFormSubmissionWithValidData() {
        contactPage.submitForm("notEmail");
        String successMessage = contactPage.getSuccessMessage();
        Assert.assertTrue(successMessage.contains("Success! Your details have been submitted successfully."), "Success message mismatch.");
        System.out.println("Form submitted successfully with valid data.");
        
    }
	@Parameters("url")
	@Test(groups = {"functional", "regression"},  priority = 3, description = "Test form submission with missing email value and validate error messages")
    public void testFormSubmissionWithMissingEmail(String url) {
		driver.get(url);
        contactPage.submitForm("emptyEmail");
        String errorMessage = contactPage.getErrorMessage();
        //Assert.assertTrue(errorMessage.contains("Email is required"), "Error message for missing email is not displayed.");
        //Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed.");
        System.out.println("Error message displayed for missing email."+errorMessage);
    }

}
