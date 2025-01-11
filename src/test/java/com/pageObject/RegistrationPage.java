package com.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
	WebDriver driver;

	// Constructor
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	// Step 1: Fill out New User Signup form
	String name = "John Doe";
	String email = "johndoe" + System.currentTimeMillis() + "@example.com";

	// Step 2: Fill out Account Information form
	String password = "Password123";
	String day = "10";
	String month = "January";
	String year = "1990";
	String firstName = "John";
	String lastName = "Doe";
	String company = "Example Corp";
	String address1 = "123 Example Street";
	String address2 = "Apt 456";
	String state = "California";
	String city = "Los Angeles";
	String zipcode = "90001";
	String mobileNumber = "1234567890";

	// Locators for New User Signup form
	@FindBy(xpath = "//input[@name='name']")
	private WebElement nameInput;

	@FindBy(xpath = "//input[@data-qa='signup-email']")
	private WebElement emailInput;

	@FindBy(xpath = "//button[normalize-space()='Signup']")
	private WebElement signupButton;

	// Locators for Enter Account Information form
	@FindBy(id = "id_gender1") // Male gender radio button
	private WebElement genderMale;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(id = "days")
	private WebElement daysDropdown;

	@FindBy(id = "months")
	private WebElement monthsDropdown;

	@FindBy(id = "years")
	private WebElement yearsDropdown;

	@FindBy(id = "newsletter")
	private WebElement newsletterCheckbox;

	@FindBy(id = "optin")
	private WebElement specialOffersCheckbox;

	@FindBy(id = "first_name")
	private WebElement firstNameInput;

	@FindBy(id = "last_name")
	private WebElement lastNameInput;

	@FindBy(id = "company")
	private WebElement companyInput;

	@FindBy(id = "address1")
	private WebElement address1Input;

	@FindBy(id = "address2")
	private WebElement address2Input;

	@FindBy(id = "state")
	private WebElement stateInput;

	@FindBy(id = "city")
	private WebElement cityInput;

	@FindBy(id = "zipcode")
	private WebElement zipcodeInput;

	@FindBy(id = "mobile_number")
	private WebElement mobileNumberInput;

	@FindBy(xpath = "//button[normalize-space()='Create Account']")
	private WebElement createAccountButton;

	@FindBy(xpath = "//h2[normalize-space()='Account Created!']")
	private WebElement accountCreatedMessage;

	// Actions
	public void fillSignupForm() {
		nameInput.sendKeys(name);
		emailInput.sendKeys(email);
		signupButton.click();
	}

	public void fillAccountInformation() {
		genderMale.click();
		passwordInput.sendKeys(password);
		daysDropdown.sendKeys(day);
		monthsDropdown.sendKeys(month);
		yearsDropdown.sendKeys(year);
		newsletterCheckbox.click();
		specialOffersCheckbox.click();
		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(lastName);
		companyInput.sendKeys(company);
		address1Input.sendKeys(address1);
		address2Input.sendKeys(address2);
		stateInput.sendKeys(state);
		cityInput.sendKeys(city);
		zipcodeInput.sendKeys(zipcode);
		mobileNumberInput.sendKeys(mobileNumber);
		createAccountButton.click();
	}

	public boolean isAccountCreated() {
		return accountCreatedMessage.isDisplayed();
	}
}
