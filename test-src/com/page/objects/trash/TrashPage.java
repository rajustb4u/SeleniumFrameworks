package com.page.objects.trash;

import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
import com.testng.Assert;

import com.dataprovider.ConfigManager;
import com.page.locators.TrashLocators;
import com.page.objects.home.HomePage;
import com.selenium.SafeActions;


public class TrashPage extends SafeActions implements TrashLocators
{
	private WebDriver driver;
	ConfigManager config;	
	
	//Constructor to define/call methods	 
	public TrashPage(WebDriver driver) throws Exception
	{		
		super(driver);
		this.driver = driver;
		config = new ConfigManager();
    } 


	/**
	 * Purpose- To verify whether trash page is being displayed or not
	 * @throws Exception
	 */
	public HomePage verifyTrashPage() throws Exception
	{
		waitUntilElementDisappears(LOADING);
		boolean bTrashPage = isElementPresent(TRASH_PAGE, SHORTWAIT);
		Assert.assertTrue(bTrashPage,"The verification message on trash page is not being displayed on clicking trash link");
		return new HomePage(driver);
	}
}
