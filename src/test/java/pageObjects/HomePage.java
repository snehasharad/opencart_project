package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage (WebDriver driver)
	{
		super(driver); //this invokes parent base class constructor
	}
	
	//elements
	@FindBy(xpath="//span[contains(text(),'My Account')]")
	WebElement lnkMyaccount;
	
	@FindBy(linkText="Register")
	WebElement lnkRegister;
	
	@FindBy(linkText="Login")  //step6
	WebElement lnkLogin;
	
	@FindBy(xpath="//input[@placeholder='Search']")  //For Search Product Test
	WebElement txtSearchbox;
	
	@FindBy(xpath="//div[@id='search']//button[@type='button']") //For Search Product Test
	WebElement btnSearch;
	
	//action methods
	public void clickMyAccount()
	{
		lnkMyaccount.click();
	}
	
	public void clickRegister()
	{
		lnkRegister.click();
	}
	
	public void enterProductName(String pName)   //For Search Product Test
	{
		txtSearchbox.sendKeys(pName);
	}
	
	public void clickSearch()  //For Search Product Test
	{
		btnSearch.click();
	}
	
	public void clickLogin()
	{
		lnkLogin.click();
	}
}
