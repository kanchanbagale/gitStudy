package com.flipkart.Tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.flipkart.BusinessFlows.Login_BusinessFlows;
import com.flipkart.Reporting.Report_Setup;
import com.flipkart.Utilities.Common_Functions;
import com.flipkart.Utilities.Driver_Setup;
import com.flipkart.Utilities.WrapperMethods;
public class Login_Test extends Driver_Setup {
	public WebDriver driver;
	WrapperMethods method = new WrapperMethods();
	@Parameters({ "browserType", "appURL", "tcID" })
	@Test
	public void TC_Login(String browserType, String appURL, String TestCase_ID) throws Throwable{
		try
		{
			
			Login_BusinessFlows page_login = new Login_BusinessFlows();
			Common_Functions commonFunctions = new Common_Functions();
			Driver_Setup n = new Driver_Setup();
			Report_Setup.InitializeReport(TestCase_ID);
			driver = n.initializeTestBaseSetup(browserType, appURL, TestCase_ID);
			commonFunctions.startRecording();
			// calling login method from Login_flow
			page_login.Login(driver, TestCase_ID);
			Report_Setup.extent.endTest(Report_Setup.test);
			Report_Setup.extent.flush();
			commonFunctions.stopRecording();
		}
		catch (Exception e)
		{
			Common_Functions commonFunctions = new Common_Functions();
			commonFunctions.stopRecording();
			Report_Setup.extent.endTest(Report_Setup.test);
			Report_Setup.extent.flush();
			driver.close();
			System.out.println("TC_Login_ Error");
		}
		
	}
	@AfterTest
	void close_driver() {
		driver.quit();

	}
	
}
