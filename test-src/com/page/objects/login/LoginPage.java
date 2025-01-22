package com.page.objects.login;

import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
import com.testng.Assert;

import com.page.locators.LogInLocators;
import com.page.objects.home.HomePage;
import com.selenium.SafeActions;


public class LoginPage extends SafeActions implements LogInLocators
{
	private WebDriver driver;
	
	
	//Constructor to define/call methods	 
	public LoginPage(WebDriver driver) throws Exception
	{ 
		
		super(driver);
		this.driver = driver;

    } 
	
	/**
	 * Purpose- To verify whether gmail login page is being displayed or not
	 * @throws Exception
	 */
	public void verifyLoginPage() throws Exception
	{
		boolean bIsSignInButtonExists = isElementPresent(SIGNIN_BTN, SHORTWAIT);
		Assert.assertTrue(bIsSignInButtonExists,"Sign In button is not being displayed on Login page");
	}
	
	/**
	 * Purpose- To enter login credentilas i.e., username and password 
	 * @param sUsername- we pass username of gmail application
	 * @param sPassword- we pass passowrd of gmail application
	 * @throws Exception
	 */
	public void enterLoginCredentials(String sUsername, String sPassword) throws Exception
	{
		if(!safeGetAttribute(EMAIL_FIELD, "value", MEDIUMWAIT).equalsIgnoreCase(sUsername))
		{
			safeType(EMAIL_FIELD, sUsername, SHORTWAIT);	
		}
		safeType(PASSWORD_FIELD, sPassword, SHORTWAIT);
	}
	
	/**
	 * Purpose- To click on Sign In button
	 * @throws Exception
	 */
	public HomePage clickSignInButton() throws Exception
	{
		safeClick(SIGNIN_BTN, SHORTWAIT);
		waitForPageToLoad(LONGWAIT);
		return new HomePage(driver);
	}
}
