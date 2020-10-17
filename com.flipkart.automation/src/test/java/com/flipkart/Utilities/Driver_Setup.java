package com.flipkart.Utilities;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


@SuppressWarnings("unused")
public class Driver_Setup {
	
	public WebDriver driver;
	public String driverPath = Constants.driverPath;
    public String TC_ID=null;
    public String Url=null;
    public String browser_Type=null;
	
	@SuppressWarnings("deprecation")
	public WebDriver initIEDriver(String appURL) {
		System.out.println("Launching Internet Explorer with new profile..");
		System.setProperty("webdriver.ie.driver", driverPath+ "IEDriverServer.exe");
		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		//Log.info(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS);
		cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		//cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,true);
		cap.setCapability("nativeEvents", false);    
		cap.setCapability("unexpectedAlertBehaviour", "accept");
		cap.setCapability("ignoreProtectedModeSettings", true);
		cap.setCapability("enable-popup-blocking", true);
		cap.setCapability("enablePersistentHover", true);
		cap.setCapability("ignoreZoomSetting", true);
		cap.setJavascriptEnabled(true);	
		//cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		//cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		driver = new InternetExplorerDriver(cap);
		driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().to(appURL);
		return driver;
	}
	
	public WebDriver initChromeDriver(String appURL) {
		System.out.println("Launching google chrome with new profile..");		
		System.setProperty("webdriver.chrome.driver", driverPath+ "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	public WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		System.setProperty("webdriver.gecko.driver", driverPath+ "geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to(appURL);
		return driver;
	}

	@Parameters({ "browserType", "appURL","tcID" })
	public WebDriver initializeTestBaseSetup(String browserType, String appURL,String tcID) {
		try {
			
			switch(browserType) {
			case "IE":
				driver = initIEDriver(appURL);
				break;
			case "CHROME":
				driver = initChromeDriver(appURL);
				break;
			case "FIREFOX":
				driver = initFirefoxDriver(appURL);
				break;
			default:
				System.out.println("browser : " + browserType
						+ " is invalid, Launching Chrome as default for execution...");
				driver = initChromeDriver(appURL);
			
				TC_ID=tcID;
			Url=appURL;
			browser_Type=browserType.toUpperCase();
			}
		}
			catch (Exception e) {
			System.out.println("Error while launching browser....." + e.getStackTrace());
			driver.close();
		}
		return driver;
	}

}
