package com.page.objects.inbox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
import com.testng.Assert;

import com.dataprovider.ConfigManager;
import com.page.locators.InboxLocators;
import com.page.objects.home.HomePage;
import com.selenium.Dynamic;
import com.selenium.SafeActions;


public class InboxPage extends SafeActions implements InboxLocators
{
	private WebDriver driver;
	ConfigManager config;
	
	
	//Constructor to define/call methods	 
	public InboxPage(WebDriver driver) throws Exception
	{		
		super(driver);
		this.driver = driver;
		config = new ConfigManager();
    } 



	/**
	 * Purpose- To click on email with specified subject
	 * @param sSubject - We pass the Subject of the mail 
	 * @throws Exception
	 */
	public void clickMail(String sSubject) throws Exception 
	{
		waitUntilElementDisappears(LOADING, MEDIUMWAIT);
		boolean bMail = isElementPresent(By.xpath(".//span/b[text() = '" + sSubject + "']"),MEDIUMWAIT);
		Assert.assertTrue(bMail,"Unread Email with specified subject is not being displayed in Inbox");
		safeClick(By.xpath(".//span/b[text() = '" + sSubject + "']"),MEDIUMWAIT);
	}
	
	/**
	 * Purpose- To verify opened mail with specified subject and body
	 * @param sSubject- we pass the subject of the mail
	 * @param sBody- we pass the body text of the mail
	 * @throws Exception
	 */
	public HomePage verifyOpenedMail(String sSubject, String sBody) throws Exception
	{
		boolean bSubject = isElementPresent(Dynamic.getNewLocator(EMAILSUBJECT, sSubject), MEDIUMWAIT);
		Assert.assertTrue(bSubject,"Opened email doesn't have the specified subject");			
		boolean bBody = isElementPresent(Dynamic.getNewLocator(EMAILBODY, sBody), MEDIUMWAIT);
		Assert.assertTrue(bBody,"Opened email with specified subject doesn't have the specified body");
		return new HomePage(driver);
	}
}
