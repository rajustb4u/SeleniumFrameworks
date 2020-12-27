package com.page.objects.contacts;

import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
import com.testng.Assert;

import com.dataprovider.ConfigManager;
import com.page.locators.ContactsLocators;
import com.page.objects.home.HomePage;
import com.selenium.SafeActions;


public class ContactsPage extends SafeActions implements ContactsLocators
{
	private WebDriver driver;
	ConfigManager config;	
	
	//Constructor to define/call methods	 
	public ContactsPage(WebDriver driver) throws Exception
	{		
		super(driver);
		this.driver = driver;
		config = new ConfigManager();
    } 

	
	/**
	 * Purpose- To verify whether contacts page is being displayed or not
	 * @throws Exception
	 */
	public void verifyContactsPage() throws Exception
	{
		waitUntilElementDisappears(LOADING);
		boolean bContactsPage =  isElementPresent(ADDCONTACTS_BTN, MEDIUMWAIT);
		Assert.assertTrue(bContactsPage,"Add contacts button is not being displayed/recognized on contacts page");
	}
	
	/**
	 * Purpose- To add contacts from contact page
	 * @param sContactName- we pass contact name
	 * @throws Exception
	 */
	public void addContacts(String sContactName) throws Exception
	{	
		safeClick(ADDCONTACTS_BTN);
		if(isElementVisible(CONTACT_TXTAREA))
			safeClick(CONTACT_TXTAREA);
			safeType(CONTACT_TXTAREA, sContactName);
		safeClick(ADD_BTN);
		boolean bAdded = isElementPresent(ADDED_MSG, MEDIUMWAIT);
		Assert.assertTrue(bAdded,"The message 'contact has been added successfully' is not being displayed after clicking on Add Button");
	}
	
	
	/**
	 * Purpose- To delete the contacts from contact page
	 * @throws Exception
	 */
	public HomePage deleteContacts() throws Exception
	{
		Thread.sleep(1000);
		safeClick(CHECKBOX, MEDIUMWAIT);
		isElementPresent(EMAIL, SHORTWAIT);
		safeClick(MORE_BTN, MEDIUMWAIT);
		safeClick(DELETECONTACTS, LONGWAIT);		
		boolean bDeleted = isElementPresent(DELETED_MSG, SHORTWAIT);
		Assert.assertTrue(bDeleted,"The message 'Contact has been deleted successfully' is not being displayed after clicking on Delete Contacts option");
		return new HomePage(driver);
	}
}
