package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

	
	//group testing
	@Test(groups= {"Regression","Master"})
	void test_account_Registration()
	{
		logger.debug("Application logs......");
		logger.info("***  Starting TC_001_AccountRegistrationTest *** ");
		try
		{
		//using hp object we access homepage methods
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My account link");
		
		hp.clickRegister();
		logger.info("Clicked on register link");
		
		//using regpage object we can access registration methods
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		logger.info("providing customer data");
		regpage.setFirstName(randomeString().toUpperCase());
		
		regpage.setLastName(randomeString().toUpperCase());
		
		regpage.setEmail(randomeString()+"@gmail.com");
		
		regpage.setTelephone(randomeNumber());
		
		String password=randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password); //if we give directly randomAlphaNumeric as option in both password and confirmpassword it will be conflict or it wont match so store in one string and use it
		
		regpage.setPrivacyPolicy();
		
		regpage.clickContinue();
		logger.info("Clicked on continue");
		
		String confmsg=regpage.getConfirmationMsg();
		
		logger.info("validating expected message");
		Assert.assertEquals(confmsg, "Your Account Has Been Created!","test failed");
		}
		catch(Exception e)
		{
			logger.error("test failed");
			Assert.fail();
		}
		logger.info("***  Finished TC_001_AccountRegistrationTest *** ");
	}
	
	
}
