package com.flipkart.BusinessFlows;

import org.openqa.selenium.WebDriver;

import com.flipkart.Locators.Login_Locators;
import com.flipkart.Reporting.Extent_Reporting;
import com.flipkart.Utilities.Excel_Handling;
import com.flipkart.Utilities.WrapperMethods;

public class Login_BusinessFlows {
	WebDriver driver;
	WrapperMethods method = new WrapperMethods();

	public void Login(WebDriver driver, String TC_ID) throws Throwable {

		try {

			Thread.sleep(60000);
			System.out.println("-----------Login into the application--------------");
			// Entering Username
			System.out.println("Getting username from excel-" + Excel_Handling.Get_Data(TC_ID, "username"));
			method.waitForElementClickable(driver, Login_Locators.username_TextField, "username");
			method.inputText(driver, Login_Locators.username_TextField, Excel_Handling.Get_Data(TC_ID, "username"),
					"Username");
			Extent_Reporting.Log_Pass_with_Screenshot("Entering UserName successful", "Pass", driver);
			Thread.sleep(500);
			
			// entering Password
			System.out.println("Getting password from excel-" + Excel_Handling.Get_Data(TC_ID, "password"));
			method.waitForElementClickable(driver, Login_Locators.password_TextField, "Password");
			method.inputText(driver, Login_Locators.password_TextField, Excel_Handling.Get_Data(TC_ID, "password"),
					"Password");
			Extent_Reporting.Log_Pass_with_Screenshot("Entering Password successful", "Pass", driver);

			// Thread.sleep(3000);
			System.out.println("Clicking on Login button");
			//method.waitForElementClickable(driver, Login_Locators.login_button, "Login button");
			method.Clickbtn(driver, Login_Locators.login_button, "Login Button");
			System.out.println("--------------------------------------------------");
			Extent_Reporting.Log_Pass_with_Screenshot("Clicking on Login Button", "Pass", driver);

		}
		// Thread.sleep(4000);
		//
		// WebDriverWait wait2 = new WebDriverWait(driver, 3);
		// wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Dashboard_Talentric_Page.home_page_current_user)));
		// WebElement current_user_logo =
		// driver.findElement(By.xpath(Dashboard_Talentric_Page.home_page_current_user));
		// if (current_user_logo.isDisplayed()) {
		// Extent_Reporting.Log_Pass_with_Screenshot("Logged in Successfully", "Passed",
		// driver);
		// System.out.println("Logged in Successfully");
		// } else {
		// Extent_Reporting.Log_Fail("Login Error", "Failed", driver);
		// System.out.println("Login Failed");
		// }
		//

		catch (Exception e) {
			Extent_Reporting.Log_Fail("Login Error", "Failed", driver);
			System.out.println("Login Failed");
		}
	}
}
