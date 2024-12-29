package com.pages;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class AdminClass extends BaseClass {

	public AdminClass(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver,this);	
	}
	//Admin Page and dashboard locators
    
		//Unorder left side list
		@FindBy(xpath="//li[@class='oxd-main-menu-item-wrapper']")
		List<WebElement>menuOptions;
		
		//Admin Option 
		@FindBy(xpath="//span[text()='Admin']")
		public
	
		WebElement adminOption;
		
		//After clicking admin admin header
		@FindBy(xpath="//h6[text()='Admin']")
		public
		WebElement adminHeader;
		
		/*Admin Page locator */
		
		//Search Username textbox
		@FindBy(xpath="(//input[@class='oxd-input oxd-input--active'])[2]")
		public WebElement searchTextBox;
		
		
		//search button
		@FindBy(xpath="//button[text()=' Search ']")
		public WebElement searchBtn;
		
		//no of records in user grid after search
		
		@FindBy(xpath="//div[@class='oxd-table-body']")
		List<WebElement>totalRecords;
		
		//no of record found header after search
		@FindBy(xpath="(//span[@class='oxd-text oxd-text--span'])[1]")
		public WebElement recordfoundHeader;
		
		//user role drop down
		@FindBy(xpath="(//div[@class='oxd-select-text-input'])[1]")
		WebElement roledrpdwn;
		
		//Admin option from drop down
		@FindBy(xpath="//div[contains(@role,'listbox')]")
		WebElement admindropdownOotion;
		
		//User Status drop down
		@FindBy(xpath="(//i[@class=\"oxd-icon bi-caret-down-fill oxd-select-text--arrow\"])[2]")
		WebElement statusdrpdwn;
		
		//status drop down option select
		@FindBy(xpath="//span[text()='Enabled']")
		WebElement statusDropdownOption;
		
		/*Action Method*/

		@Override
		public void clickElement(WebElement ele) {
			// TODO Auto-generated method stub
			ele.click();
		}
		
		public int getOptionsCount()
		{
			return menuOptions.size();
		}
			
		//Get the no of records
		public int getRecordsCount()
		{
			return totalRecords.size();
		}
		
		//click on role dropdown and select admin role
		public void searchWithAdminRole()
		{
			roledrpdwn.click();
			addWait();
			clickElement(admindropdownOotion);
			addWait();
			clickElement(searchBtn);
			addWait();
		}
		
		
		//Click on status drop down and select Enabled status
		public void searchWithStatus()
		{
			clickElement(statusdrpdwn);
			addWait();
			clickElement(statusDropdownOption);
			addWait();
			clickElement(searchBtn);
			addWait();
		}
		

}
