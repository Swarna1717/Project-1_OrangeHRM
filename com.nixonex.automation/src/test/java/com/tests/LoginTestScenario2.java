package com.tests;

import org.testng.annotations.Test;

import com.pages.AdminClass;

import com.pages.LoginClass;

import com.utils.PropertiesReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

public class LoginTestScenario2 {
	WebDriver driver;
	LoginClass login=new LoginClass(driver);
	PropertiesReader prop;
	AdminClass ad=new AdminClass(driver);
	
  

  @BeforeTest
  public void beforeTest() {
	  prop=new PropertiesReader();
	  ChromeOptions options = new ChromeOptions();
  	options.addArguments("--remote-allow-origins=*");

  	System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver-win64\\chromedriver.exe");
  	 driver = new ChromeDriver(options);
	  driver.get(prop.getData("url"));
	  login.addWait();
	  driver.manage().window().maximize();
	  login=new LoginClass(driver);
  }
  
       @Test(priority=1)
        public void loginTest()
       {
    	   login.login(prop.getData("username"), prop.getData("password"));
    	   
       }
       
       @Test(priority=2)
       public void validateUrl()
       {
    	   String url=login.appUrl();
    	   Assert.assertEquals(url,"https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", "Url not matched. Test failed ");
    	   System.out.println("Url Matched launch success. Test Passed");
       }
       
       
       @Test(priority=3)
       public void validateTitle()
       {
    	   System.out.println("Page Tilte: "+login.appTitle());
    	   Assert.assertEquals(login.appTitle().contains("OrangeHRM"),true, "Title not matched. Test failed");
    	   System.out.println("Title matched launch success.  Test passed");
       }
       
       
     @AfterClass
     public void tearDown() {
    	 if(driver!=null)
    		 driver.quit();
    	 else
    		 System.out.println("Driver is null");
     }

}
