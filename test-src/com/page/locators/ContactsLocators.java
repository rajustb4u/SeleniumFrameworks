package com.page.locators;

import org.openqa.selenium.By;

public interface ContactsLocators
{
	public static By LOADING = By.xpath(".//span[text() = 'Loading...']");
	public static By CONTACTS_PAGE = By.xpath(".//td[contains(text(),'Welcome to Contacts')]");
	public static By ADDCONTACTS_BTN = By.cssSelector("div[aria-label^='Add to ']");
	public static By CONTACT_TXTAREA = By.cssSelector("textarea[aria-haspopup='true']");
	public static By ADD_BTN = By.xpath(".//div[text() = 'Add']");
	public static By UNDO_LNK_I = By.id("link_undo");
	public static By ADDED_MSG = By.xpath(".//div[contains(text(), 'has been added')]");
	public static By CHECKBOX = By.xpath("//div[3]/div/div/div/div/div/div/table/tbody/tr/td[2]/div/div");
	public static By MORE_BTN = By.xpath(".//div[text() = 'More']");
	public static By DELETECONTACTS = By.xpath(".//div[text() = 'Delete contact']");
	public static By DELETED_MSG = By.xpath(".//div[contains(text(), 'has been deleted')]");
	public static By EMAIL = By.xpath(".//div[@aria-label='Email']");
}
