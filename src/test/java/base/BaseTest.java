package base;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Ashish Tripathi
 *
 */
@Slf4j
public class BaseTest extends AbstractTestNGCucumberTests {

	public static WebDriver driver;

	/*
		This Before method make sure to
		initialize driver at the start of suit execution
	 */

	@BeforeTest(alwaysRun = true)
	@Parameters ({"browser"})
	public void setUpDriver(@Optional("chrome") String browser) {
		log.info("Driver Init");
		if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {// Running in chrome if no browser defined in testng.xml or chrome defined
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	/*
		After Method to close driver
		at the end of execution
	 */

	@AfterSuite()
	public void tearDown() {
		log.info("Driver Quit");
		driver.quit();
	}

	@AfterMethod()
	public void takeScreenshotFailed(ITestResult scenario){
		if(!scenario.isSuccess()){
			takeScreenShot();
		}
	}

	public void takeScreenShot(){
		DateFormat dateFormat = new SimpleDateFormat("dd-MM:HH:mm:ss");
		Date date = new Date();
		String timeStamp = dateFormat.format(date);
		String fileName = timeStamp +".png";
		log.info("Taking Screenshot for: " + fileName);
		try {
			File destiny = new File("./target/ScreenshotsFailure/"+fileName);
			log.info(destiny.toString());
			FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), destiny);
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}
}
