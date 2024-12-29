package com.tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.utils.Excel;


public class LoginTest {
	WebDriver driver;
	Excel excelUtil;
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeClass
	public void setUp() throws IOException {
		excelUtil = new Excel();
		ChromeOptions options = new ChromeOptions();
    	options.addArguments("--remote-allow-origins=*");

    	System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver-win64\\chromedriver.exe");
    	 driver = new ChromeDriver(options);
		  driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  driver.manage().window().maximize();
		  ExtentSparkReporter htmlReport =new  ExtentSparkReporter("ExtentReport/OrangeHRMReport.html");
		  extent = new ExtentReports();
		  extent.attachReporter(htmlReport);	    
	    }
	
      @Test(dataProvider="LoginTestData", dataProviderClass = Excel.class)
      public void loginTest(String username, String password) {
    	  
	  test=extent.createTest("Login test with username:"+username);
	  driver.findElement(By.name("username")).sendKeys(username);
	  driver.findElement(By.name("password")).sendKeys(password);
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	  driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	  //Assert and Validate Data
	  if(username.equals("Admin") && password.equals("admin123"))
	  {
		 Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		 WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[normalize-space()='Dashboard']")));
		 takeScreenshot("LoginAttempt_"+username);
		 test.pass("Valid Credetials Login successful");
		 logout();
		 
	  }
	  else
	  {
		  WebElement errorMessage= driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/p"));
		  Assert.assertTrue(errorMessage.isDisplayed(),"Invalid credentials");
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		  takeScreenshot("LoginAttempt_"+username);
		  test.fail("Invalid Credetials-Login failed as expected");
	  }	  
	    
    }
         
	public void logout() {
		// TODO Auto-generated method stub
		driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
	}
	

	public void takeScreenshot(String testName) {
		// TODO Auto-generated method stub
		File screeShot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String timestamp=new SimpleDateFormat("yyyymmddHHmmss").format(new Date());
		String filepath="Screenshots/"+testName+"_"+timestamp+".png";
		try {
			FileUtils.copyFile(screeShot, new File(filepath));
			test.addScreenCaptureFromPath(filepath);
		   } 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void tearDown() throws IOException
	{
		excelUtil.closeWorkbook();
		if(driver!=null)
		{
			driver.quit();
		}
		extent.flush();
	}
		
}