package com.page.locators;

import org.openqa.selenium.By;

public interface HomeLocators 
{
	//public static String GMAIL_USER_BTN_IX ="gbgs4dn";
	public static By GMAIL_USER_BTN = By.cssSelector("a[title='zseleniumframework@gmail.com']");
	public static By COMPOSE_BTN = By.xpath("//div[contains(text(), 'COMPOSE')]");
	public static By DRAFTS_LINK = By.xpath(".//a[contains(text(),'Drafts')]");	
	public static By MORE_LNK = By.xpath(".//span[@role='button']/span[text() = 'More']");
	public static By TRASH_LNK = By.xpath(".//a[text() = 'Trash']");
	public static By GMAIL_DRP =  By.xpath(".//div[@role = 'button']/span[text() = 'Gmail']");
	public static By CONTACTS =  By.xpath(".//div[text() = 'Contacts']");
	public static By TERMANDPRIVACY_LNK = By.linkText("Terms & Privacy");
	public static By LEGALNOTICES_LNK = By.linkText("Legal Notices");
	public static By PRIVACYPOLICY_LNK = By.linkText("Privacy Policy");
	public static By PROGRAMPOLICIES_LNK = By.linkText("Program Policies");
	public static By TERMSOFSERVICE_LNK = By.linkText("Terms of Service");
	public static By DETAILS_LNK =  By.xpath(".//span[text() = 'Details']");
	public static By ACTIVITYACCOUNT_LNK =  By.xpath(".//span[text() = 'Activity on this account']");
	public static By INBOX_LNK =  By.xpath(".//a[contains(text(),'Inbox')]");
	public static By GMAIL_SIGNOUT_BTN = By.id("gb_71");	
	public static By GMAIL_SIGNIN_BTN = By.id("signIn");
}
