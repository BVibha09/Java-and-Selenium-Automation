package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.commons.Constants;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.CommonUtil;

public class LoginTest{
	
	BasePage basePage;
	WebDriver driver;
	Properties prop;
	LoginPage loginPage;
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		driver.get(prop.getProperty("url"));
		CommonUtil.mediumwait();
		loginPage = new LoginPage(driver);
	}
	
	@Test(priority=1)
	public void verifyLoginPageTitleTest(){
		
		String title = loginPage.getLoginPageTitle();
		System.out.println("Title of the page is: " +title);
		Assert.assertEquals(title, Constants.LOGINPAGE_TITLE, "Login page title is not correct");
	}
	
	@Test(priority=2)
	public void verifySignUpLinkText(){
		Assert.assertTrue(loginPage.verifySignUpLink(), "Sign up link is not visible");
	}
	
	@Test(priority=3)
	public void loginTest(){
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
