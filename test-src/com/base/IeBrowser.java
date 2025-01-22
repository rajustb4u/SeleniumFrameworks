package com.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


/**
 * This class defines all methods required to initialize IEDriver
 */
public class IeBrowser 
{
	private static Logger log = Logger.getLogger("IeBrowser");
	static String fileSeperator = System.getProperty("file.separator");
	static DesiredCapabilities capabilities;
	
	
	/**
	 * 
	 * This method is used initiate IE browser
	 *
	 * @return , returns IE browser driver object
	 */
	public static WebDriver init()
	{
		setCapabilities();
		log.info("Launching IE with default settings");
		WebDriver driver = new InternetExplorerDriver(capabilities);
		return driver;
	}
	/**
	 * Sets the required properties for IEdriver to initialize 
	 * @return IECapabilities with required properties set
	 */
	private static void setCapabilities() 
	{
		System.setProperty("webdriver.ie.driver", getIEDriverPath());    
		capabilities = new DesiredCapabilities();
		capabilities.setCapability("ignoreProtectedModeSettings", true);
		capabilities.setCapability("enablePersistentHover", false);
		capabilities.setCapability("native_events", false);
	}
	
	/**
	 * 
	 * This method is used to get IEDriverServer.exe file location
	 *
	 * @return , returns IEDriverServer.exe file path
	 */
	private static String getIEDriverPath() 
	{
		return System.getProperty("user.dir")+fileSeperator+"Drivers"+fileSeperator+"IEDriverServer.exe";
	}
}
