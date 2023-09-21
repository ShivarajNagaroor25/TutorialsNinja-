package com.tutorialninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialninja.qa.utils.Utilities;

public class Base {

	WebDriver driver;
	public Properties prop;
	public Properties dataProp;

	public Base() {

		prop=new Properties();
		File file=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\config\\config.properties");

		try {
			FileInputStream propFileInput=new FileInputStream(file);
			prop.load(propFileInput);
		}
		catch(Throwable e) {
			e.printStackTrace();
		}

		dataProp=new Properties();
		File dataFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\Testdata.properties");

		try {
			FileInputStream dataFileInput=new FileInputStream(dataFile);
			dataProp.load(dataFileInput);
		}

		catch(Throwable e) {
			e.printStackTrace();
		}
	}

	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {

		String Browser="chrome";
		if (Browser.equalsIgnoreCase("chrome")) {
			driver= new ChromeDriver();
		}else if (Browser.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}else if (Browser.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME)); 
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));

		return driver;
	}
}
