package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	//Objects
	@FindBy(xpath ="//span[text()='My Account']")
	private WebElement myAccoutDropMenu;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(linkText = "Register")
	private WebElement registerOption;

	@FindBy(xpath = "//input[@class='form-control input-lg']")
	private WebElement searchTextField;

	@FindBy(xpath = "//div[@id='search']/descendant::button")
	private WebElement clickOnSearchButton;


	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//Actions
	public void clickOnMyAccountDropMenu() {
		myAccoutDropMenu.click();
	}

	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	public LoginPage nagivateToLoginPage() {
		myAccoutDropMenu.click();
		loginOption.click();
		return new LoginPage(driver);
	}

	public RegisterPage clickOnRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage() {
		myAccoutDropMenu.click();
		registerOption.click();
		return new RegisterPage(driver);
	}

	public void searchTextField(String productName) {
		searchTextField.sendKeys(productName);
	}

	public SearchPage clickOnSearchButton() {
		clickOnSearchButton.click();
		return new SearchPage(driver);
	}
}
