package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

	@Test(groups= {"Sanity","Master"})
	public void test_login()
	{
		try {
		logger.info("****starting TC_002_LoginTest *****");
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on my account");
		
		hp.clickLogin();
		logger.info("clicked on login");
		
		LoginPage lp=new LoginPage(driver);
		logger.info("providing login details");
		
		lp.setEmail(rb.getString("email")); //valid email from config.properties file
		lp.setPassword(rb.getString("password")); //same as above
		lp.clickLogin();
		logger.info("clicked on login button");
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetpage=macc.isMyAccountPageExists();
		Assert.assertEquals(targetpage,true);
	}
	catch(Exception e)
	{
		Assert.fail();
	}
		logger.info("**** finished TC_002_LoginTest****");
	}
}
