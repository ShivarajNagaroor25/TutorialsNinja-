package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.utils.Utilities;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;

public class LoginTest extends Base{

	public LoginTest() {
		super();	
	}

	public WebDriver driver;
	LoginPage loginPage;


	@BeforeMethod
	public void setUp() {
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage=new HomePage(driver);
		loginPage=homePage.nagivateToLoginPage();
	}

	@AfterMethod
	public void TearDown() {
		driver.quit();
	}
	@DataProvider(name="validCredentialSupplier")
	public Object[][] supplyTestData() {
		Object [][] data= Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 1,dataProvider="validCredentialSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {

		loginPage.login(email, password);

		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		
		loginPage.login(Utilities.generateEmailTimeStamp(), dataProp.getProperty("invalidPassword"));
	
		String actualWarningMessage= loginPage.retrieveEmailPasswordNotWarningMessage();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "Expected Warning Message is not displayed");
	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		loginPage.login(Utilities.generateEmailTimeStamp(), prop.getProperty("validPassword"));

		String actualWarningMessage=loginPage.retrieveEmailPasswordNotWarningMessage();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "Expected Warning Message is not displayed");
	}

	@Test(priority = 4)
	public void verifyLoginWithvalidEmailAndInValidPassword() {
		
		loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		
		String actualWarningMessage=loginPage.retrieveEmailPasswordNotWarningMessage();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "Expected Warning Message is not displayed");
	}

	@Test(priority = 5)
	public void verifyLoginWithoutValues() {
		loginPage.clickOnLoginButton();

		String actualWarningMessage=loginPage.retrieveEmailPasswordNotWarningMessage();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "Expected Warning Message is not displayed");
	}

}
