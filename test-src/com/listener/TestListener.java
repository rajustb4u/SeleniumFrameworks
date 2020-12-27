package com.listener;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.dataprovider.ConfigManager;
import com.utilities.ReportSetup;
import com.utilities.ScreenCapture;
import com.utilities.UtilityMethods;
import com.testng.Assert;

public class TestListener extends TestListenerAdapter
{
	
	private  static char cQuote = '"';
	ConfigManager sys = new ConfigManager();
	ConfigManager depend = new ConfigManager("TestDependency");
	private  static String fileSeperator = System.getProperty("file.separator");
	Logger log =Logger.getLogger("TestListener");
   	
	/**
	 * This method will be called if a test case is failed. 
	 * Purpose - For attaching captured screenshots and videos in ReportNG report 
	 */
	public void onTestFailure(ITestResult result)
	{
		depend.writeProperty(result.getName(),"Fail");

		log.error("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" );
		log.error("ERROR ----------"+result.getName()+" has failed-----------------" );
		log.error("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" );
		ITestContext context = result.getTestContext();
		WebDriver driver = (WebDriver)context.getAttribute("driver");
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.setCurrentTestResult(result);
		
		String imagepath = ".." + fileSeperator+"Screenshots" + fileSeperator + ScreenCapture.saveScreenShot(driver);
		Reporter.log("<a href="+cQuote+imagepath+cQuote+">"+" <img src="+cQuote+imagepath+cQuote+" height=48 width=48 ></a>");
		ScreenCapture.stopVideoCapture(result.getName());
		UtilityMethods.verifyPopUp();
		String sValue = new ConfigManager().getProperty("VideoCapture");
		String sModeOfExecution = new ConfigManager().getProperty("ModeOfExecution");
		if(sValue.equalsIgnoreCase("true") && sModeOfExecution.equalsIgnoreCase("linear"))
		{
			String sVideoPath = null;
			sVideoPath = testCaseVideoRecordingLink(result.getName());
			Reporter.log("<a href="+cQuote+sVideoPath+cQuote+" style="+cQuote+"text-decoration: none; color: white;"+cQuote+"><div class = cbutton>Download Video</div></a>");
			Reporter.log("<font color='Blue' face='verdana' size='2'><b>"+Assert.doAssert()+"</b></font>");
//			Reporter.log("<a color='Blue' face='verdana' size='2'><b>"+Assert.doAssert()+"</b></a>");
		}
		Reporter.setCurrentTestResult(null);
		
	}
	
	/**
	 * This method will be called if a test case is skipped. 
	 * 
	 */
	public void onTestSkipped(ITestResult result)
	{			
		log.warn("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" );
		log.warn("WARN ------------"+result.getName()+" has skipped-----------------" );
		log.warn("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" );			

		depend.writeProperty(result.getName(),"Skip");
	
		//************* comment below code if you are using TestNG dependency methods

		Reporter.setCurrentTestResult(result);
		ScreenCapture.stopVideoCapture(result.getName());
		UtilityMethods.verifyPopUp();
		Reporter.setCurrentTestResult(null);
	}
	
	/**
	 * This method will be called if a test case is passed. 
	 * Purpose - For attaching captured videos in ReportNG report 
	 */
	public void onTestSuccess(ITestResult result)
	{
		depend.writeProperty(result.getName(),"Pass");
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		log.info("###############################################################" );
		log.info("SUCCESS ---------"+result.getName()+" has passed-----------------" );
		log.info("###############################################################" );
		Reporter.setCurrentTestResult(result);
		ScreenCapture.stopVideoCapture(result.getName());
		UtilityMethods.verifyPopUp();
		String sValue = new ConfigManager().getProperty("VideoCapture");
		String sModeOfExecution = new ConfigManager().getProperty("ModeOfExecution");
		if(sValue.equalsIgnoreCase("true")&&sModeOfExecution.equalsIgnoreCase("linear"))
		{
			 String sVideoPath = testCaseVideoRecordingLink(result.getName());
			 Reporter.log("<a href="+cQuote+sVideoPath+cQuote+" style="+cQuote+"text-decoration: none; color: white;"+cQuote+"><div class = cbutton>Download Video</div></a>");
		}
		Reporter.setCurrentTestResult(null);
	}
	
	/**
	 * This method will be called before a test case is executed. 
	 * Purpose - For starting video capture and launching balloon popup in ReportNG report 
	 */
	public void onTestStart(ITestResult result)
	{
		log.infoLevel("<h2>**************CURRENTLY RUNNING TEST************ "+result.getName()+"</h2>" );
		ScreenCapture.startVideoCapture();		
		UtilityMethods.currentRunningTestCaseBalloonPopUp(result.getName());
	}
		
	public void onStart(ITestContext context) 
	{
		
	}
		
	public void onFinish(ITestContext context) 
	{
		/*
		Reporter.log("<br>");
		String currentLine;
		//String logFilePath="..\\..\\..\\Log\\Log.log";
		File directory = new File (".");
		String = directory.getCanonicalPath()+"\\Log\\Log.log";
		BufferedReader bufferedReader = null;
		bufferedReader = new BufferedReader(new FileReader(logFilePath));
		while((currentLine=br.readLine())!=null)
		{
			Reporter.log(currentLine+"<br>"+"<Font size=1>");					
		}
		bufferedReader.close();
		*/			
	}

	/**
	 * 
	 * To identify the latest captured screenshot
	 *
	 * @return
	 */
	public String capturedScreenShot()
	{
		
		File mediaFolder=new File(ReportSetup.getImagesPath());
		File[] files = mediaFolder.listFiles();
	    Arrays.sort( files, new Comparator<Object>()
	    {
	    public int compare(Object o1, Object o2) {
	    	//return new Long(((File)o1).lastModified()).compareTo(new Long(((File)o2).lastModified())); // for ascending order
	    	return -1*(new Long(((File)o1).lastModified()).compareTo(new Long(((File)o2).lastModified()))); //for descending order 
	    }
	    });
	    return files[0].getName();
	}
	
	/**
	 * 
	 * This method is used to rename the captured video with test case name
	 *
	 * @param tname , Need to pass the test case name
	 * @return, Returns the captured video path name
	 */
	public  String testCaseVideoRecordingLink(String tname)
	{	
		String sVideoPath = ".." + fileSeperator + "Videos" + fileSeperator + tname + "(1).avi";		
		if(new File(ReportSetup.getVideosPath()+fileSeperator+tname+"(1).avi").exists())
		{			
			return sVideoPath;
		}
		else
		{
			String sVideoPath2 = sVideoPath.substring(0,sVideoPath.length()-7)+".avi";
			return sVideoPath2;
		}
	}
}
