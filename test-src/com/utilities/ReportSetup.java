/*************************************** PURPOSE **********************************

 - This class contains all methods related to HTML reporting
*/
package com.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.log4j.Logger;
//import org.testng.Assert;
import com.testng.Assert;



public class ReportSetup 
{
    private static String fileSeperator = System.getProperty("file.separator");
    private static Logger log = Logger.getLogger("ReportSetup");
    private static boolean isDirCreated = true;
    private static String message;
  
    /**
	* This method setup's reporting environment i.e., creating a root folder and destination folder for storing report information
	*/
	public static void createFolderStructure()
	{
		createReportsFolder();
		createLatestResultsFolder();
      	createMediaFolders(); 	
	}

	/**
    * This method creates 'Automation Reports' directory if it does not exist
    */
	public static void createReportsFolder()
	{
		File file = new File(getReportsPath());
      	if (!file.exists())
    	{
      		isDirCreated = file.mkdir();
    	}
      	if(!isDirCreated)
      	{
      		message = "\n Exception occured while creating 'Automation Results' directory";
			log.error("Check folder permissions of Project Directory..."+message);
			Assert.fail("Check folder permissions of Project Directory..."+message);
		}
	}
	
	/**
    * This method creates 'Latest Reports' directory if it does not exist
    * if directory exists it renames to Results_on_<currentDataTime> folder name and creates 'Latest Reports' directory
    */	
	public static void createLatestResultsFolder()
	{
		try 
		{
			File latestResults = new File(getLatestResultsPath());
	      	if(latestResults.exists())
	      	{
	      		Path p = Paths.get(getLatestResultsPath());
			    BasicFileAttributes view;
				
				view = Files.getFileAttributeView(p, BasicFileAttributeView.class).readAttributes();
			    String fCreationTime = view.creationTime().toString();
			    String istTime = UtilityMethods.convertToISTTime(fCreationTime.split("\\.")[0].replace("T","-"));
	      		String oldFolder = getReportsPath() + fileSeperator + "Results_on_" + istTime.replace(":", "_at_");
	      		File oldResults = new File(oldFolder);
	      		latestResults.renameTo(oldResults);
	      		
	      	}
	      	isDirCreated = latestResults.mkdir();
	      	if(!isDirCreated)
	      	{
	      		message = "\n Exception occured while creating 'Latest Results' directory";
				log.error("Check folder permissions of Project Directory..."+message);
				Assert.fail("Check folder permissions of Project Directory..."+message);
			}
		} 
		catch (IOException e) 
		{
			log.error("Exception occured while creating 'Latest Results' directory or unable to rename current 'Latest Results' directory "+e.getCause());
			Assert.fail("Exception occured while creating 'Latest Results' directory or unable to rename current 'Latest Results' directory "+e.getCause());
		}
	}
	

	/**
    * This method creates 'videos, screenshots' directory if they does not exist
    */	
	public static void createMediaFolders()
	{
		File videosFolder = new File(getVideosPath());
		if(!videosFolder.exists())
		{
			isDirCreated = videosFolder.mkdir();
		}
      	if(!isDirCreated)
      	{
      		message = "\n Exception occured while creating 'Latest Results/videos' directory";
			log.error("Check folder permissions of Project Directory..."+message);
			Assert.fail("Check folder permissions of Project Directory..."+message);
		}
		File imagesFolder = new File(getImagesPath());
		if(!imagesFolder.exists())
		{
			isDirCreated = imagesFolder.mkdir();
		} 
      	if(!isDirCreated)
      	{
      		message = "\n Exception occured while creating 'Latest Results/screenshots' directory";
			log.error("Check folder permissions of Project Directory..."+message);
			Assert.fail("Check folder permissions of Project Directory..."+message);
		}
	}
	
	
	/**
	 *@return - This method returns path to the folder where screen recordings are stored
	 */
	public static String getVideosPath()
	{	
		return getLatestResultsPath()+fileSeperator+"Videos";
	}

	/**
	 *@return - This method returns path to the folder where screenshots are stored
	 */
	public static String getImagesPath()
	{
		return getLatestResultsPath()+ fileSeperator + "Screenshots";
	}


	/**
	 *@return - This method returns path to the folder where latest results are stored 
	 */
	public static String getLatestResultsPath()
	{
		return getReportsPath() + fileSeperator + "LatestResults";
	}
	
	/**
	 *@return - This method returns the path to the root of reports folder
	 */
	public static String getReportsPath()
	{
		return System.getProperty("user.dir") + fileSeperator + "Automation Reports";	
	}
	
	
	
	
	
	

	/**
	 * Purpose - To Mail the test summary file after completion of reporting
	 * @throws Exception
	 
	private static void emailHtmlSummaryReport() throws Exception
	{
		if(sys.getProperty("EmailConfig.SendMail").equalsIgnoreCase("true"))
		{
			try{	
				toZipDir(sLatestFolder, sLatestFolder);
			    String smtpHost = sys.getProperty("EmailConfig.smtpHost");
			    String popHost = sys.getProperty("EmailConfig.popHost");
			    String from = sys.getProperty("EmailConfig.from"); // with @yahoo.com
			    String to = sys.getProperty("EmailConfig.to");
			    //String username = "vikas.reddy@zenqa.com";//Enter username
			    String password = sys.getProperty("EmailConfig.password");//Enter your yahoo password
			    String fileAttachment = sRoot_folder + sSeperator + sNewfoldername+".zip";    
			    System.out.println(fileAttachment);
			    // Get system properties
			    Properties props = System.getProperties();
		
			    // Setup mail server
			    props.put("mail.smtp.host", smtpHost);
			    props.put("mail.smtp.auth","true");
		
			    // Get session
			    Session session = Session.getDefaultInstance(props, null);
			    session.setDebug(true);
		
			    // Pop Authenticate yourself
			    Store store = session.getStore("pop3");
			    store.connect(popHost, from, password);
		
			    // Define message
			    MimeMessage message = new MimeMessage(session);
			    message.setFrom(new InternetAddress(from));
			    message.addRecipient(Message.RecipientType.TO, 
			      new InternetAddress(to));
			    message.setSubject("Selenium Automation Results");
			    //message.setText("Welcome to Yahoo's JavaMail");
			    
			    // create the message part 
			    MimeBodyPart messageBodyPart = 
			      new MimeBodyPart();
		
			    //fill message
			    messageBodyPart.setText("The automation build has completed successfully. Please find enclosed Summary Report");
		
			    Multipart multipart = new MimeMultipart();
			    multipart.addBodyPart(messageBodyPart);
		
			    // Part two is attachment
			    messageBodyPart = new MimeBodyPart();
			    DataSource source = 
			      new FileDataSource(fileAttachment);
			    messageBodyPart.setDataHandler(
			      new DataHandler(source));
			    messageBodyPart.setFileName("Summary Report.zip");
			    multipart.addBodyPart(messageBodyPart);
		
			    // Put parts in message
			    message.setContent(multipart);    
		
			    // Send message
			    //Transport.send(message);
			    Transport transport = session.getTransport("smtp");
			    transport.connect(smtpHost, from, password);
			    transport.sendMessage(message, message.getAllRecipients());
			}
			catch(Exception e)
			{
				System.out.println("unable to send an email");
				System.out.println("exception:"+ e.getMessage());
				UtilityMethods.infoBox(e.getMessage(), "Exception While Sending Email");
			}			
		}

	}

	private static void toZipDir(String origDir , String destDir)
	{
		try {
			File sLatestZip = new File(destDir +".zip");
			if(sLatestZip.exists())
			{
				sLatestZip.delete();
			}
			ZipOutputStream out =
				new ZipOutputStream( 
					new FileOutputStream(destDir +".zip" ) );
			zipDir( origDir, new File( origDir ), out );
			out.close();
		} catch ( Exception e ) {
			e.printStackTrace();
		} 
	}


	private static void zipDir( String origDir, File dirObj, ZipOutputStream out )throws Exception
	{
		File[] files = dirObj.listFiles();
		byte[] tmpBuf = new byte[1024];

		for ( int i = 0; i < files.length; i++ )
		{
			if ( files[i].isDirectory() ) 
			{
				zipDir( origDir, files[i], out );
				continue;
			}			
			
			String wAbsolutePath = files[i].getAbsolutePath().substring( origDir.length(),
			files[i].getAbsolutePath().length() );
			FileInputStream in = new FileInputStream( files[i].getAbsolutePath() );
			out.putNextEntry( new ZipEntry( wAbsolutePath ) );
			int len;
			while ( (len = in.read( tmpBuf )) > 0 )
			{
				out.write( tmpBuf, 0, len );
			}	
			out.closeEntry();
			in.close();
		
		}
	}
	*/

}
	


