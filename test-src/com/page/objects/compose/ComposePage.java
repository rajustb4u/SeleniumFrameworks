package com.page.objects.compose;

import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
import com.testng.Assert;

import com.dataprovider.ConfigManager;
import com.page.data.SendMailData;
import com.page.locators.ComposeLocators;
import com.page.objects.home.HomePage;
import com.selenium.SafeActions;
import com.selenium.Sync;


public class ComposePage extends SafeActions implements ComposeLocators
{
	private WebDriver driver;
	ConfigManager config;	
	SendMailData filldata;	
	Sync sync;
	
	//Constructor to define/call methods	 
	public ComposePage(WebDriver driver) throws Exception
	{		
		super(driver);
		this.driver = driver;
		config = new ConfigManager();		
    } 

	
	/**
	 * Purpose- To verify whether compose a new mail page is being displayed or not
	 * @throws Exception
	 */
	public void verifyComposePage() throws Exception 
	{
		waitUntilElementDisappears(LOADING);
		boolean bIsSendButtonExists = isElementPresent(SEND_BTN, MEDIUMWAIT);
		Assert.assertTrue(bIsSendButtonExists,"Sendbutton doesn't exist");
	}
	
	/**
	 * Purpose- To enter email address,subject and mail text in To,Subject and Body fields 
	 * @param sUser -  we pass Email address of the user
	 * @param sSubject- we pass Subject for the mail
	 * @param sBody- we pass body text for the mail
	 * @throws Exception
	 */
	public void enterTo_Subject_BodyFields(SendMailData filldata) throws Exception 
	{
		fillSendMaildata(filldata);		
	}
	
	/**
	 * Purpose- To click on send button
	 * @throws Exception
	 */
	public void clickSendButton() throws Exception
	{
		safeClick(SEND_BTN, SHORTWAIT);
	}
	
	/**
	 * Purpose- To verify whether an email is sent successfully or not
	 * @throws Exception
	 */
	public HomePage verifySentMailMessage() throws Exception
	{
		boolean bViewMailLink = isElementPresent(VIEWMSG_LINK, LONGWAIT);
		boolean bSentMsgSuccess = isElementPresent(MSG_SENT_MESSAGE);
		Assert.assertTrue(bViewMailLink,"'View Message' link is not being displayed on clicking 'Send' button");
		Assert.assertTrue(bSentMsgSuccess,"The verification message- 'Your message has been sent' is not being displayed on clicking 'Send' button");
		return new HomePage(driver);
	}
	
	/**
	 * Purpose- To click on send button and to verify saved message,saved button
	 * @throws Exception
	 */
	public HomePage clickSaveButton() throws Exception
	{
		safeClick(SAVENOW_BTN, SHORTWAIT);
		return new HomePage(driver);
	}
	
	
	/**
	 * Purpose - To fill data in the send mail fields by calling from set and get methods
	 * @param datafields
	 * @throws Exception
	 */
	
	public void fillSendMaildata(SendMailData datafields) throws Exception
	{	
		safeType(TO_FIELD, datafields.getsToAddress(), SHORTWAIT);
		safeType(SUBJECT_FIELD, datafields.getsSubject(), SHORTWAIT);	    
		selectFrame(COMPOSEFRAME,MEDIUMWAIT);
	    safeType(BODYFRAME, datafields.getsMessageBody(), SHORTWAIT);
	    defaultFrame();            
	}

}

