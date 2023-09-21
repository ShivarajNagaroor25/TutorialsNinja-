package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	//Objects
	@FindBy(id="input-firstname")
	private WebElement enterFirstNameTextField;

	@FindBy(id="input-lastname")
	private WebElement enterLastNameTextField;

	@FindBy(id="input-email")
	private WebElement enterValidEmailTextField;

	@FindBy(id="input-telephone")
	private WebElement enterTelephoneTextField;

	@FindBy(id="input-password")
	private WebElement enterPasswordTextField;

	@FindBy(id="input-confirm")
	private WebElement enterConfirmPasswordTextField;

	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement selectPrivacyPolicy;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement clickOnSubmitButton;

	@FindBy(xpath = "//label[text()='Yes']")
	private WebElement clickOnYesRadioButton;

	@FindBy(xpath = "//label[text()='Yes']")
	private WebElement clickOnNoRadioButton;

	@FindBy(xpath = "//div[contains(@class,'col-sm-')]//div[text()='First Name must be between 1 and 32 characters!']")
	private WebElement enterFirstNameMandetoryTextField;

	public RegisterPage(WebDriver driver) {
		this.driver=driver;

		PageFactory.initElements(driver, this);
	}

	//Actions
	public void enterFirstNameTextField(String firstName) {
		enterFirstNameTextField.sendKeys(firstName);
	}

	public void enterLastNameTextField(String lastName) {
		enterLastNameTextField.sendKeys(lastName);
	}

	public void enterValidEmailTextField(String email) {
		enterValidEmailTextField.sendKeys(email);
	}

	public void enterTelephoneTextField(String telephone) {
		enterTelephoneTextField.sendKeys(telephone);
	}

	public void enterPasswordTextField(String password) {
		enterPasswordTextField.sendKeys(password);
	}

	public void enterConfirmPasswordTextField(String confirmPassword) {
		enterConfirmPasswordTextField.sendKeys(confirmPassword);
	}
	
	
	public AccountSuccessPage registerWithMandetoryFields(String firstName,String lastName,String email,String telephone,String password,String confirmPassword) {
		enterFirstNameTextField.sendKeys(firstName);
		enterLastNameTextField.sendKeys(lastName);
		enterValidEmailTextField.sendKeys(email);
		enterTelephoneTextField.sendKeys(telephone);
		enterPasswordTextField.sendKeys(password);
		enterConfirmPasswordTextField.sendKeys(confirmPassword);
		selectPrivacyPolicy.click();
		clickOnSubmitButton.click();
		return new AccountSuccessPage(driver);
	}

	public AccountSuccessPage registerWithAllFieldsSubscribeYes(String firstName,String lastName,String email,String telephone,String password,String confirmPassword) {
		enterFirstNameTextField.sendKeys(firstName);
		enterLastNameTextField.sendKeys(lastName);
		enterValidEmailTextField.sendKeys(email);
		enterTelephoneTextField.sendKeys(telephone);
		enterPasswordTextField.sendKeys(password);
		enterConfirmPasswordTextField.sendKeys(confirmPassword);
		clickOnYesRadioButton.click();
		selectPrivacyPolicy.click();
		clickOnSubmitButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerWithAllFieldsSubscribeNo(String firstName,String lastName,String email,String telephone,String password,String confirmPassword) {
		enterFirstNameTextField.sendKeys(firstName);
		enterLastNameTextField.sendKeys(lastName);
		enterValidEmailTextField.sendKeys(email);
		enterTelephoneTextField.sendKeys(telephone);
		enterPasswordTextField.sendKeys(password);
		enterConfirmPasswordTextField.sendKeys(confirmPassword);
		clickOnNoRadioButton.click();
		selectPrivacyPolicy.click();
		clickOnSubmitButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public void selectPrivacyPolicy() {
		selectPrivacyPolicy.click();
	}

	public AccountSuccessPage clickOnSubmitButton() {
		clickOnSubmitButton.click();
		return new AccountSuccessPage(driver);
	}

	public void clickOnYesRadioButton() {
		clickOnYesRadioButton.click();
	}

	public void clickOnNoRadioButton() {
		clickOnNoRadioButton.click();
	}
	
	public String enterFirstNameMandetoryTextField() {
		String mandetoryTextField= enterFirstNameMandetoryTextField.getText();
		return mandetoryTextField;
	}
}







