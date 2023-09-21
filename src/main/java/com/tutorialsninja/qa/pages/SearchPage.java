package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;
	
	//Object
	@FindBy(linkText = "iMac")
	private WebElement validProductName;
	
	@FindBy(xpath = "//p[text()='There is no product that matches the search criteria.']")
	private WebElement resultForInvalidSearchedProduct;
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String displayeStatusForvalidProductName() {
		String productName= validProductName.getText();
		return productName;
	}
	
	public String resultForInvalidSearchedProduct() {
		String InvalidSearchedWarningMessage= resultForInvalidSearchedProduct.getText();
		return InvalidSearchedWarningMessage;
	}
}
