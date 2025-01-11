package com.pageObject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	WebDriver driver;

    // Predefined input values
    private String name = "John Doe";
    private String email = "johndoe@example.com";
    private String subject = "Test Subject";
    private String message = "Test Message";

    public ContactPage(WebDriver driver) {
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

    @FindBy(xpath = "//input[@name='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@class='status alert alert-success']")
    private WebElement successMessage;

    @FindBy(xpath = "//div[@class='status alert alert-danger']")
    private WebElement errorMessage;
    
    // Actions
    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    // Generalized function for filling and submitting the form
    public void submitForm(String checkmail) {
        if(checkmail.equalsIgnoreCase("notEmail")) {
        	nameField.clear();
            nameField.sendKeys(name);
            emailField.clear();
            emailField.sendKeys(email);
            subjectField.clear();
            subjectField.sendKeys(subject);
            messageField.clear();
            messageField.sendKeys(message);
            submitButton.click();
          
        }else if(checkmail.equalsIgnoreCase("emptyEmail")) {
        	nameField.clear();
            nameField.sendKeys(name);
            emailField.clear();
            subjectField.sendKeys(subject);
            messageField.clear();
            messageField.sendKeys(message);
            submitButton.click();
        }   
        Alert alert = driver.switchTo().alert();
        alert.accept(); // Clicks "ok"
    }
    
    public String getSuccessMessage() {
        return successMessage.getText();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
