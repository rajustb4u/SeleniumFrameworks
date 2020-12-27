package com.page.objects.home;

import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
import com.testng.Assert;

import com.dataprovider.ConfigManager;
import com.page.locators.HomeLocators;
import com.page.objects.compose.ComposePage;
import com.page.objects.contacts.ContactsPage;
import com.page.objects.drafts.DraftsPage;
import com.page.objects.inbox.InboxPage;
import com.page.objects.login.LoginPage;
import com.page.objects.trash.TrashPage;
import com.selenium.SafeActions;


public class HomePage extends SafeActions implements HomeLocators
{
	private WebDriver driver;
	ConfigManager config;	
	
	//Constructor to define/call methods	 
	public HomePage(WebDriver driver) throws Exception
	{		
		super(driver);
		this.driver = driver;
		config = new ConfigManager();
    } 
	
	/**
	 * Purpose- To verify whether login is successful or not
	 * @throws Exception
	 */
	public void verifyLogin() throws Exception
	{
			//UtilityMethods.preserveMethodName();
			boolean bIsGmailUserNameExists = isElementPresent(GMAIL_USER_BTN, LONGWAIT);
			Assert.assertTrue(bIsGmailUserNameExists,"Username is not being displayed after clicking on SignIn button");
	}
	
	/**
	 * Purpose- To click on compose button
	 * @throws Exception
	 */
	public ComposePage clickComposeButton() throws Exception
	{		
		safeClick(COMPOSE_BTN, SHORTWAIT);	
		return new ComposePage(driver);
	}
	
	/**
	 * Purpose- To navigate to Drafts page
	 * @throws Exception
	 */
	public DraftsPage clickDraftsLink() throws Exception
	{
		Thread.sleep(1000);
		safeClick(DRAFTS_LINK,SHORTWAIT);
		return new DraftsPage(driver);
	}
	
	/**
	 * Purpose- To navigate to trash page
	 * @throws Exception
	 */
	public TrashPage navigateToTrashPage() throws Exception
	{
		//UtilityMethods.preserveMethodName();
		safeClick(MORE_LNK,SHORTWAIT);
		safeClick(TRASH_LNK,SHORTWAIT);
		return new TrashPage(driver);
	}
	
	/**
	 * Purpose- To navigate to contacts page
	 * @throws Exception
	 */
	public ContactsPage navigateToContactsPage() throws Exception
	{
		safeClick(GMAIL_DRP, MEDIUMWAIT);
		safeClick(CONTACTS, LONGWAIT);
		return new ContactsPage(driver);
	}
	
	/**
	 * Purpose- To navigate to Inbox
	 * @throws Exception
	 */
	public InboxPage clickInboxLink() throws Exception
	{
		safeClick(INBOX_LNK, SHORTWAIT);
		return new InboxPage(driver);
	}
	
	/**
	 * Purpose- To verify Details footer link
	 * @throws Exception
	 */
	public void verifyDetailsFooterLink() throws Exception 
	{
		String sWinHandleBefore = driver.getWindowHandle();
		Thread.sleep(5000);
		safeClick(DETAILS_LNK, SHORTWAIT);
		for(String sWinHandle : driver.getWindowHandles())
		{
			driver.switchTo().window(sWinHandle);
		}
		waitForPageToLoad();
		boolean bDetailspage = isElementPresent(ACTIVITYACCOUNT_LNK, SHORTWAIT);
		Assert.assertTrue(bDetailspage,"On clicking Footer link- Details, Account details page is not being displayed");
		driver.close();
		driver.switchTo().window(sWinHandleBefore);
	}

	/**
	 * Purpose to verify terms and privacy footer link
	 * @throws Exception
	 */
	public void verifyTermsAndPrivacyLink() throws Exception 
	{
		String sWinHandleBefore = driver.getWindowHandle();
		safeClick(TERMANDPRIVACY_LNK,SHORTWAIT);
		for(String sWinHandle : driver.getWindowHandles())
		{
			driver.switchTo().window(sWinHandle);
		}
		waitForPageToLoad();
		Assert.assertTrue(isAllLinksOnTermsAndPrivacyPagedisplayed(),"Terms and Privacy page is not being displayed successfully on clicking Terms & Privacy footer link");
		driver.close();
		driver.switchTo().window(sWinHandleBefore);
	}
	
	/**
	 * Purpose- to verify the links on Terms and privacy page
	 * @return
	 * @throws Exception
	 */
	public boolean isAllLinksOnTermsAndPrivacyPagedisplayed() throws Exception
	{
		boolean bLegalNotices = isElementPresent(LEGALNOTICES_LNK, SHORTWAIT);
		boolean bPrivacyPolicy = isElementPresent(PRIVACYPOLICY_LNK, SHORTWAIT);
		boolean bProgramPolicies = isElementPresent(PROGRAMPOLICIES_LNK, SHORTWAIT);
		boolean bTermsofService = isElementPresent(TERMSOFSERVICE_LNK, SHORTWAIT);
		boolean bAllLinks = (bLegalNotices && bPrivacyPolicy && bProgramPolicies && bTermsofService);
		return bAllLinks;
	}
	
	/**
	 * Purpose- click on Username and click on logout button
	 * @throws Exception
	 */
	public void clickLogOutButton() throws Exception 
	{	
		safeClick(GMAIL_USER_BTN, SHORTWAIT);
		safeClick(GMAIL_SIGNOUT_BTN, SHORTWAIT);
		waitForPageToLoad();
		
	}
	
	/**
	 * Purpose- To verify whether logout is successful or not
	 * @throws Exception
	 */
	public LoginPage verifyLogOut() throws Exception 
	{
		boolean bLogout = isElementPresent(GMAIL_SIGNIN_BTN, MEDIUMWAIT);		
		Assert.assertTrue(bLogout,"Sign In button is not being displayed on Login page after clicking on SignOut button");
		return new LoginPage(driver);
	}
}
