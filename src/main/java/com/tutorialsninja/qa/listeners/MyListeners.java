package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialninja.qa.utils.ExtentRepoter;

public class MyListeners implements ITestListener{
	ExtentReports extentRepoter;
	ExtentTest extentTest;
	@Override
	public void onStart(ITestContext context) {
		extentRepoter = ExtentRepoter.generateExtentReport();
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName=result.getName();
		extentTest = extentRepoter.createTest(testName);
		extentTest.log(Status.INFO, testName+" start executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName=result.getName();
		extentTest.log(Status.PASS, testName+" got sucessfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName=result.getName();

		System.out.println("Screenshot taken");
		WebDriver driver=null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenShotPath=System.getProperty("user.dir")+"\\Screenshot\\"+testName+".png";
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenShotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		extentTest.addScreenCaptureFromPath(destinationScreenShotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+" Got Fail");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName=result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+" Got Skipped");
	}


	@Override
	public void onFinish(ITestContext context) {
		
		extentRepoter.flush();
		String pathOfExtentReport=System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentreport.html";
		File extentReport=new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
