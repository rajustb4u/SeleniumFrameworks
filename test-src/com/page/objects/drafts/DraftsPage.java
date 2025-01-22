package com.page.objects.drafts;

import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
import com.testng.Assert;

import com.dataprovider.ConfigManager;
import com.page.locators.DraftsLocators;
import com.page.objects.home.HomePage;
import com.selenium.Dynamic;
import com.selenium.SafeActions;


public class DraftsPage extends SafeActions implements DraftsLocators
{
	private WebDriver driver;
	ConfigManager config;
	
	
	//Constructor to define/call methods	 
	public DraftsPage(WebDriver driver) throws Exception
	{		
		super(driver);
		this.driver = driver;
		config = new ConfigManager();
    } 

	
	/**
	 * Purpose- To verify whether drafts page is being displayed or not
	 * @throws Exception
	 */
	public void verifyDraftsPage() throws Exception
	{
		boolean bDraftsPage = isElementPresent(DRAFT_PAGE, MEDIUMWAIT);
		Assert.assertTrue(bDraftsPage,"Drafts Page is not being displayed on clicking Drafts link");
	}
	
	/**
	 * Purpose- To verify whether the saved mail is being displayed in drafts or not
	 * @param sSubject - we pass the Subject of the mail  
	 * @throws Exception
	 */
	public HomePage verifySavedMailInDrafts(String sSubject) throws Exception
	{		
		boolean bSavedMail = isElementPresent(Dynamic.getNewLocator(SUBJECTLINE, sSubject), SHORTWAIT);
		Assert.assertTrue(bSavedMail,"The mail with specified subject is not being displayed in drafts page");
		return new HomePage(driver);
	}
}
