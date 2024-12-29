package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory; 

public abstract class BaseClass {
	
	protected WebDriver driver;

	public BaseClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	 
	//Encapsulation for WebDriver actions
	
	public abstract void clickElement(WebElement ele);
	
	protected void enterText(WebElement ele1, String text)
	{
		ele1.sendKeys(text);
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