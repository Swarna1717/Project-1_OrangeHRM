package com.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginClass extends BaseClass{
	//Constructor to initialize Loginpage
			public LoginClass(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

			// Locators on login page name and xpath
			@FindBy(name="username")
			WebElement uName;
			
			@FindBy(name="password")
			WebElement pswrd; 
			
			@FindBy(xpath="//button[normalize-space()='Login']")
			WebElement loginBtn;
			
			
			//Action Method on main page
			
			public void login(String username, String password){
				enterText(uName,username);
				enterText(pswrd,password);
				clickElement(loginBtn);
				addWait();
			}
			
			@Override
			public void clickElement(WebElement we) {
				// TODO Auto-generated method stub
				we.click();
			}
			
			public void refreshPage()
			{
				driver.navigate().refresh();
			}
			
			public String appUrl()
			{
				String url=driver.getCurrentUrl();
				return url;
			}
			
			public String appTitle() {
				
				String title=driver.getTitle();
				return title;
				
			}
			
			public void addWait()
			{
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			

}
