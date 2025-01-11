package com.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.pageObject.RegistrationPage;
import com.testBase.BaseClass;

public class RegistrationTestCase extends BaseClass {
	RegistrationPage registrationPage;

	@BeforeClass
	public void setupPageObject() {
		registrationPage = new RegistrationPage(driver);
	}

	@Parameters("url")
	@Test(priority = 1, description = "Verify that the Registration page loads successfully")
	public void testRegistrationPageLoadsSuccessfully(String url) {
		driver.get(url);
		String title = registrationPage.getPageTitle(driver);
		Assert.assertEquals(title, "Automation Exercise - Signup / Login", "Registration page title mismatch.");
		System.out.println("Registration page loaded successfully with title: " + title);
	}


}
