package com.testsuite.email;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.BaseSetup;
import com.dataprovider.ConfigManager;
import com.page.locators.ComposeLocators;
import com.page.objects.compose.ComposePage;
import com.page.objects.home.HomePage;
import com.page.objects.login.LoginPage;
import com.selenium.SafeActions;
import com.selenium.Sync;
import com.testng.Retry;

public class EmailverificationusingHelperpropertiesfile extends BaseSetup implements ComposeLocators
{

	LoginPage loginPage;
	HomePage homePage; 
	ConfigManager app;
	SafeActions methods;
	Properties prop;
	
	Retry retry;
	
	
	
	
	@BeforeMethod (groups = { "regression" })
	public void BaseClassSetUp() throws Exception
	{
		loginPage = new LoginPage(getDriver());
		//_prop = new ConfigManager();
		app = new ConfigManager("App");		
		homePage = new HomePage(getDriver());
		methods = new SafeActions(getDriver());
		prop = new Properties();
		//Properties properties = new Properties();
		
		
	}
	
	@Test (groups = "regression")
	public void SendMailtoOutsider() throws Exception
	
	{
		//ReportHelper.setsTestname("Send Mail");
		
		getDriver().get(app.getProperty("App.URL"));
		
		(new Sync(getDriver())).waitForPageToLoad();
		
		loginPage.verifyLoginPage();
		
		loginPage.enterLoginCredentials(app.getProperty("App.Username"),app.getProperty("App.Password"));
		
		HomePage homePage = loginPage.clickSignInButton();
		
		ComposePage composePage =homePage.clickComposeButton();
		Thread.sleep(7000);
		composePage.verifyComposePage();
		
		fillMaildata();
		GmailLogout();
		
	
	}
		
		public void fillMaildata() throws Exception
		{
			//UtilityMethods.preserveMethodName();			
			methods.safeType(TO_FIELD, app.getProperty("App.User"), SHORTWAIT);
			methods.safeType(SUBJECT_FIELD, app.getProperty("App.Subject"), SHORTWAIT);
			Thread.sleep(3000);
			//_driver.switchTo().frame(_driver.findElement(By.xpath(".//iframe")));
			//_driver.switchTo().frame(_driver.findElement(By.xpath(".//iframe[@tabindex='1']")));
		    //_methods.SafeTypeByCSS(BODYFRAME_CSS, _helper.getHelperProp("SenderMessageBody"), "SHORTWAIT");
	        //_driver.switchTo().defaultContent();
	        methods.safeClick(SEND_BTN, SHORTWAIT);
	        Thread.sleep(3000);
	        app.writeProperty("MAILSUBJECT1", app.getProperty("App.Subject1"));
	        System.out.println("sub1:"+app.getProperty("MAILSUBJECT1"));
	        app.writeProperty("MAILSUBJECT3", app.getProperty("App.Subject"));
	        System.out.println("sub3:"+app.getProperty("MAILSUBJECT3"));
	        app.writeProperty("MAILSUBJECT2", app.getProperty("App.Subject"));
	        System.out.println("sub2:"+app.getProperty("MAILSUBJECT2"));
		}	
	
	
	public void GmailLogout() throws Exception
	{
		//Logout from Gmail
		homePage.clickLogOutButton();
		
		homePage.verifyLogOut();
		
	}
}
		

	
	
	

