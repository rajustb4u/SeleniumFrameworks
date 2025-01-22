package com.testsuite.other;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.BaseSetup;
import com.dataprovider.ConfigManager;
import com.page.objects.home.HomePage;
import com.page.objects.login.LoginPage;
import com.page.objects.trash.TrashPage;
import com.selenium.Sync;
import com.testng.Assert;
import com.testng.Retry;




//Each TestSuite class must do the following
//1. It should extends the "BaseClass", so that @BeforeClass - BrowserInitialization, 
//	 launch, URL Navigation and @AfterClass - Browser Quit happens automatically
//2. It should create instances for respective PageParts classes along with LoadProperties class
//3. Create @BeforeMethod setup to initalize instances with getDriver(taken from Baseclass)

public class OtherLinks extends BaseSetup
{

//Declaration of respective PageParts instances along with configmanager instance


	LoginPage loginPage;
	ConfigManager app;
	HomePage homePage;
	
	
//Initialize Pageparts instances with Webdrivers getter method - getDriver()
	@BeforeMethod (groups = { "regression" })
	public void BaseClassSetUp() throws Exception
	{

		loginPage = new LoginPage(getDriver());
		app = new ConfigManager("App");
		homePage = new HomePage(getDriver());
		//lStartTime = StopWatch.start();
		//ScreenVideoCapture.startVideoCapture();
		
		getDriver().manage().deleteAllCookies();
		getDriver().get(app.getProperty("App.URL"));
		(new Sync(getDriver())).waitForPageToLoad();
	}
			
	
	//Each test method in TestSuite files must follow the below guidelines
	//1. Each test method be grouped. e.g (groups = {"smoke"})
	//2. Each test method must use ReportHelper.setsTestName("Appropriate name for your test") for Reporting purposes
	//3. Most of the content of test methods should only be self-explanatory function calls
	
	/**
	 * Purpose- This method contains the logic for 'View Trash Page' functionality in Gmail
	 * @throws Exception
	 */
	@Test (groups = "regression", retryAnalyzer=Retry.class, timeOut = 120000,priority=2)
	public void testGmailTrash() throws Exception
	{	
		//ReportHelper.setsTestname("View Trash page");		
		
		//Login to Gmail application		
		loginPage.verifyLoginPage();
		
		loginPage.enterLoginCredentials(app.getProperty("App.Username"), app.getProperty("App.Password"));
		
		HomePage homePage = loginPage.clickSignInButton();
		
		homePage.verifyLogin();
		
		//view trash page after logging in	
		TrashPage trashPage = homePage.navigateToTrashPage();
		
		//trashPage.verifyTrashPage();
		
		HomePage homePageAfterTrashPage = trashPage.verifyTrashPage();
		
		homePageAfterTrashPage.clickLogOutButton();
		
		homePageAfterTrashPage.verifyLogOut();
		Assert.assertAll();
	}
	
	
	
	
	
	/**
	 * Purpose- This method contains the logic for 'Gmail Logout' functionality in Gmail
	 * @throws Exception
	 */
	@AfterMethod
	public void testGmailLogout() throws Exception
	{
		//Logout from Gmail
		//_homePage.clickLogOutButton();
		
		//_homePage.verifyLogOut();
		
		//UtilityMethods.verifypopup();
		//lEndTime = StopWatch.stop();
		//ScreenVideoCapture.stopVideoCapture();		
		//Report.testCaseExecutiontime(ReportHelper.getsTestname(),lStartTime,lEndTime);
		//Report.testCaseVideoRecordingLink(ReportHelper.getsTestname());
	}
	
}
	
//		public void m() throws Exception{
//			Assert.haultonfailure=false;
//			Assert.assertTrue(true, "verify the corresponding email is present in inbox");
//			/*failed*/Assert.assertTrue(false, "verify the corresponding email is present in inbox");
//			Assert.assertTrue(true);
//			/*failed*/Assert.assertTrue(false);
//			/*failed*/Assert.assertFalse(true, "Verify whether the Subtect is accepting special characters(@,#,$,%,^,*,?&)");
//			Assert.assertFalse(false, "Verify whether the Subtect is accepting special characters(@,#,$,%,^,*,?&)");
//			/*failed*/Assert.assertFalse(true);
//			Assert.assertFalse(false);
//			/*failed*/Assert.assertEquals("Update on Jiff..", "We wish you a very Happy Birthday Rajan D.. Workshare.. STPI...", "Verify subject");
//			Assert.assertEquals("We wish you a very Happy Birthday Rajan D.. Workshare.. STPI...", "We wish you a very Happy Birthday Rajan D.. Workshare.. STPI...", "Verify subject");
//			/*failed*/Assert.assertEquals("Update on Jiff..", "We wish you a very Happy Birthday Rajan D.. Workshare.. STPI...");
//			Assert.assertEquals("We wish you a very Happy Birthday Rajan D.. Workshare.. STPI...", "We wish you a very Happy Birthday Rajan D.. Workshare.. STPI...");
//			Assert.assertEquals(1.11111, 1.11111, 1.1, "Verify product value in RS ");
//			Assert.assertEquals(1.11111, 1.11111, 1.1);
//			/*failed*/Assert.assertEquals(1.11111, 0.11111, 0.00, "Verify product value in RS ");
//			/*failed*/Assert.assertEquals(1.11111, 0.11111, 0.00);
//			Assert.assertEquals(1.11111f, 1.11111f, 1.1f, "Verify product value in RS ");
//			Assert.assertEquals(1.11111f, 1.11111f, 1.1f);
//			/*failed*/Assert.assertEquals(1.11111f, 0.11111f, 0.00f, "Verify product value in RS ");
//			/*failed*/Assert.assertEquals(1.11111f, 0.11111f, 0.00f);
//			Assert.assertEquals(12345678910L, 12345678910L, "Verify product value in RS ");
//			Assert.assertEquals(12345678910L, 12345678910L);
//			/*failed*/Assert.assertEquals(12345678910L, 12345678911L,  "We wish you a very Happy Birthday Rajan D.. Workshare.. STPI...");
//			/*failed*/Assert.assertEquals(12345678910L,123456789111L);
//			Assert.assertEquals(true, true, "We wish you a very Happy Birthday Rajan D.. Workshare.. STPI...");
//			Assert.assertEquals(true, true);
//			/*failed*/Assert.assertEquals(true, false, "We wish you a very Happy Birthday Rajan D.. Workshare.. STPI...");
//			/*failed*/Assert.assertEquals(true, false);
//			Assert.assertEquals(123, 123, "Verify product value in RS ");
//			Assert.assertEquals(123, 123);
//			/*failed*/Assert.assertEquals(123, 11,  "We wish you a very Happy Birthday Rajan D.. Workshare.. STPI...");
//			/*failed*/Assert.assertEquals(123,11);
//			Object[] exp={"1","2","3"};
//			Object[] act={"1","2","3"};
//			Object[] act1={"1","3","2"};
//			Object[] act2={"1","3","3"};
//			Object[] act3={"1","2"};//act, exp, "verify array objects"
//			Assert.assertEquals(act, exp,"We wish you a very Happy Birthday Rajan D.. Workshare.. STPI...");
//			Assert.assertEquals(act, exp);
//			/*failed*/Assert.assertEquals(act2, exp,"We wish you a very Happy Birthday Rajan D.. Workshare.. STPI...");
//			/*failed*/Assert.assertEquals(act2, exp);
//			/*failed*/Assert.assertEquals(act3, exp,"We wish you a very Happy Birthday Rajan D.. Workshare.. STPI...");
//			/*failed*/Assert.assertEquals(act3, exp);
//			Assert.assertEquals(act1, exp,"Hope Anurag may respond on tody on his time like where we neeed to do and what are al lthe steps need to automate then we will start doing");
//			/*failed*/Assert.assertEquals(act2, exp);
//		//	Assert.haultonfailure=true;
//			/*failed*/Assert.assertEquals(act1, exp);
//			Assert.assertAll();

//		}