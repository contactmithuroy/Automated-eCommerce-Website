package com.testCases;
import com.pageObject.RegistrationPage;
import com.testBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RegistrationTestCase extends BaseClass {
	RegistrationPage registrationPage;

	@BeforeClass
	public void setupPageObject() {
		registrationPage = new RegistrationPage(driver);
	}


	@Parameters("url")
	@Test(groups = {"sanity", "regression"}, priority = 1, description = "Verify signup successufully")
	public void testfillSignupForm(String url) {
		driver.get(url+"login");
		registrationPage.fillSignupForm();
		// Step 3: Verify Account Created message
		String currentURL = driver.getCurrentUrl();
		String expectedURL = "https://automationexercise.com/signup";
		Assert.assertEquals(currentURL, expectedURL, "SignUp failed.");
		System.out.println("Sign up successfully: ");
	}

	@Test(groups = {"sanity", "regression"}, priority = 2, description = "Verify new user registration")
	public void testNewUserRegistration() {
		registrationPage.fillAccountInformation();
		// Step 3: Verify Account Created message
		boolean accountCreated = registrationPage.isAccountCreated();
		Assert.assertTrue(accountCreated, "Account creation failed.");
		System.out.println("New account created successfully: " + accountCreated);
	}
}
