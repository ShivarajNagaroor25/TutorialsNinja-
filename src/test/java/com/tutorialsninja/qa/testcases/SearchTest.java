package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base{

	public SearchTest() {
		super();
	}

	public WebDriver driver;
	SearchPage searchPage;
	HomePage homePage;

	@BeforeMethod
	public void Setup() {
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homePage=new HomePage(driver);
	}

	@AfterMethod
	public void TearDown() {
		driver.quit();
	}

	@Test(priority=1)
	public void verifySearchingWithExistingProductName() {

		homePage.searchTextField(dataProp.getProperty("productName"));
		searchPage=homePage.clickOnSearchButton();

		String actualMessage=searchPage.displayeStatusForvalidProductName();
		String expectedMessage=dataProp.getProperty("expectedProductName");
		Assert.assertEquals(actualMessage,expectedMessage, "Searched product is not displayed");
	}

	@Test(priority=2)
	public void verifySearchingWithoutEntringProductName() {

		searchPage=homePage.clickOnSearchButton();

		String actualMessage=searchPage.resultForInvalidSearchedProduct();
		String expectedMessage=dataProp.getProperty("resultForInvalidSearchedProductabc");
		Assert.assertEquals(actualMessage,expectedMessage, "Searched product is not displayed");
	}

	@Test(priority=3, dependsOnMethods = {"verifySearchingWithoutEntringProductName"})
	public void verifySearchingWithNonExistingProductName() {

		searchPage=homePage.clickOnSearchButton();

		homePage.searchTextField(dataProp.getProperty("invalidProductName"));
		String actualMessage=searchPage.resultForInvalidSearchedProduct();
		String expectedMessage="There is no product that matches the search criteria.";
		Assert.assertEquals(actualMessage,expectedMessage, "Searched product is not displayed");
	}
}