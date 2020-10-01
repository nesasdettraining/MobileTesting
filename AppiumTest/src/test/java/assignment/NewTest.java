package assignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class NewTest {
		AndroidDriver driver;
		String username, password;
	  @Test
	  public void checkForInvalidGoogleLogin() throws Exception {
		  
		  	FileInputStream fis = new FileInputStream(new File("C:\\Users\\NesaVijayan\\eclipse-workspace\\AppiumTest\\Inputdata.xls"));
			HSSFWorkbook book = new HSSFWorkbook(fis);
			HSSFSheet sh = book.getSheet("Sheet1");
			
			HSSFRow row = sh.getRow(2);
			
			username = row.getCell(1).getStringCellValue();
			password = row.getCell(2).getStringCellValue();
		  	Thread.sleep(10000);
		  	driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().textContains(\"Sign in\")")).click();
			Thread.sleep(2000);
			driver.findElement(MobileBy.xpath("//*[@index='6']")).click();
			Thread.sleep(5000);
			
			driver.findElement(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Sign in with Google\"]")).click();
			Thread.sleep(40000);
			driver.findElement(MobileBy.className("android.widget.EditText")).sendKeys(username);
			driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().textContains(\"Next\")")).click();
			Thread.sleep(10000);
			
			driver.findElement(MobileBy.className("android.widget.EditText")).sendKeys(password);
			driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().textContains(\"Next\")")).click();
			Thread.sleep(10000);
	  }
	  
	  @BeforeTest
	  public void beforeTest() throws IOException {
		  	DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
			capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "org.khanacademy.android");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "org.khanacademy.android.ui.library.MainActivity");
			
			driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);			
			
	  }

	  @AfterTest
	  public void afterTest() {
		  String msg = driver.findElement(MobileBy.xpath(("(//android.view.View[2]/android.view.View)[3]"))).getText();
		  Assert.assertTrue(msg.contains("Wrong password"));
	  }

	}
