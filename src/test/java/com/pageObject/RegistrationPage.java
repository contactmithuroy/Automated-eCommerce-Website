package com.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
	WebDriver driver;
	// Predefined input values
    private String name = "John Doe";
    private String email = "johndoe@example.com";
    private String subject = "Test Subject";
    
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
 // Locators
    @FindBy(xpath = "//h2[normalize-space()='Get In Touch']")
    private WebElement pageTitle;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement nameField;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='subject']")
    private WebElement subjectField;

    @FindBy(xpath = "//textarea[@name='message']")
    private WebElement messageField;
    
    
    // Actions
    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }
    
}