package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.utils.Utilities;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.pages.RegisterPage;

public class RegisterTest extends Base{

	public RegisterTest() {
		super();
	}

	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;

	HomePage homePage=new HomePage(driver);

	LoginPage loginPage=new LoginPage(driver);

	@BeforeMethod
	public void SetUp() {
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage=new HomePage(driver);
		registerPage=homePage.navigateToRegisterPage();
	}

	@AfterMethod
	public void TearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisterWithMandetoryFields() {

		accountSuccessPage=registerPage.registerWithMandetoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailTimeStamp(), dataProp.getProperty("telephoneNo"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		
		String actualMessage= accountSuccessPage.accountCreatedSucessfullyMessage();
		String expectedMessage=dataProp.getProperty("accountCreatedSucessfullyMessage");
		Assert.assertEquals(actualMessage, expectedMessage, "User Sucessfully Regisgerd");

	}
	@Test(priority = 2)
	public void verifyRegisterbyEntringAllFields() {
		
		accountSuccessPage=registerPage.registerWithAllFieldsSubscribeYes(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailTimeStamp(), dataProp.getProperty("telephoneNo"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		
		String actualMessage= accountSuccessPage.accountCreatedSucessfullyMessage();
		String expectedMessage=dataProp.getProperty("accountCreatedSucessfullyMessage");
		Assert.assertEquals(actualMessage, expectedMessage, "User Sucessfully Regisgerd");

	}
	@Test(priority = 3)
	public void VerifyRegisteringAccountBySelectingNooption() {

		accountSuccessPage=registerPage.registerWithAllFieldsSubscribeNo(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailTimeStamp(), dataProp.getProperty("telephoneNo"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		
		String actualMessage= accountSuccessPage.accountCreatedSucessfullyMessage();
		String expectedMessage=dataProp.getProperty("accountCreatedSucessfullyMessage");
		Assert.assertEquals(actualMessage, expectedMessage, "User Sucessfully Regisgerd");

	}

	@Test(priority = 4)
	public void VerifyDifferentWaysOfNavigatingToRegisterAccountPage() {
		homePage.clickOnMyAccountDropMenu();
		loginPage=homePage.selectLoginOption();
		loginPage.registerForNewAccountCreationButton();

		accountSuccessPage=registerPage.registerWithAllFieldsSubscribeNo(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailTimeStamp(), dataProp.getProperty("telephoneNo"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		
		String actualMessage= accountSuccessPage.accountCreatedSucessfullyMessage();
		String expectedMessage=dataProp.getProperty("accountCreatedSucessfullyMessage");
		Assert.assertEquals(actualMessage, expectedMessage, "User Sucessfully Regisgerd");

	}

	@Test(priority = 5)
	public void VerifyRegisterPageWithoutEntringCedentials() {

		registerPage.selectPrivacyPolicy();
		registerPage.clickOnSubmitButton();

		String actualMessage= registerPage.enterFirstNameMandetoryTextField();
		String expectedMessage=dataProp.getProperty("enterMandatoryFieldsErrorMessage");
		Assert.assertEquals(actualMessage, expectedMessage, "Enter Mandetory fields");

	}
}
