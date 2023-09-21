package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage  {
	WebDriver driver;
	
	//Objects
	@FindBy(xpath = "//input[@name='email']")
	private WebElement emailAddressField;
	
	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordField;
	
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement emailPasswordNotMatch;
	
	@FindBy(linkText =  "Continue")
	private WebElement registerForNewAccountCreationButton;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public void enterEmailAddressField(String email) {
		emailAddressField.sendKeys(email);
	}
	
	public void enterPasswordField(String password) {
		passwordField.sendKeys(password);
	}
	
	public void clickOnLoginButton() {
		loginButton.click();
	}
	
	public void login(String email, String password) {
		emailAddressField.sendKeys(email);
		passwordField.sendKeys(password);
		loginButton.click();
	}
	
	public String retrieveEmailPasswordNotWarningMessage() {
		String warningText= emailPasswordNotMatch.getText();
		return warningText;
	}
	
	public void registerForNewAccountCreationButton() {
		registerForNewAccountCreationButton.click();
	}
}
