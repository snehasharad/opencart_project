package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; //logging
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver; //add static to werdriver driver for duplicating object creation
	
	public Logger logger; //for logging
	
	
	public ResourceBundle rb;//for using values from config.properties file 
	
	@BeforeClass(groups= {"Master","Sanity","Regression"})
	@Parameters("browser")
	public void setup(String br) //br variable pass the value of browser name from tentng.xml file
	{
		rb=ResourceBundle.getBundle("config");  //load config.properties file
		
		logger=LogManager.getLogger(this.getClass());//this.getclass gives current class log info
		
		//this will disable default message while launching chrome browser its not compulsory u can skip also
		//ChromeOptions options=new ChromeOptions();
		//options.setExperimentalOption("excludeSwitches",new String[] {"enable-automation"});
		
		//from 4.6.0 onwords no need to add dependency for webdriver just use single line of stmt.
		//WebDriverManager.chromedriver().setup();
		//driver=new ChromeDriver();
		
		if(br.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(br.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new FirefoxDriver();
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//driver.get("http://localhost/opencart/upload/");
		driver.get(rb.getString("appURL1")); //to hardcode url value use this
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Master","Sanity","Regression"})
	public void tearDown()
	{
		driver.quit();
	}
	
	//for random numbers and strings data for reusability they are java methods
	//except for password
	public String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);//5 means max 5 character u can change to any no.
		return(generatedString);
	}
	
	public String randomeNumber()
	{
		String generatedString2=RandomStringUtils.randomNumeric(10);//10 means it generate 10 digit numbers
		return(generatedString2);
	}
	
	//random password generation
	public String randomAlphaNumeric()
	{
		String st=RandomStringUtils.randomAlphabetic(4);
		String num=RandomStringUtils.randomNumeric(3);
		return(st+"@"+num);
	}
	
	//this will be used wenever failure occurs so just add to base class for reusability
	public String captureScreen(String tname) throws IOException {

		//SimpleDateFormat df=new SimpleDateFormat("yyyyMMddhhmmss");
		//Date dt=new Date();
		//String timestamp=df.format(dt);
		//instead of all three stmt just use only one stmt as below
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}
	
	
}
