package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.module.Browser;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;



public class Baselibrary 
{
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	 @BeforeClass(groups={"sanity","Regression","Master"})
	 @Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException 
	 { 
		 //Loading config.properties file
	   FileReader file=new FileReader("./src//test//resources//config.properties");
	   p = new Properties();
	   p.load(file);
	   
	   
	   logger=LogManager.getLogger(this.getClass());
	   
	   if(p.getProperty("excution_env").equalsIgnoreCase("remote"))
	   {
		   DesiredCapabilities cap=new DesiredCapabilities();
		   //os
		  if(os.equalsIgnoreCase("windows")) {
			  cap.setPlatform(Platform.WIN11);
		  }
		  else if(os.equalsIgnoreCase("mac")) {
			  cap.setPlatform(Platform.MAC);
		  }
		  else {
			  System.out.println("No matching os");
			  return;
		  }
		  //br
		  switch(br.toLowerCase())
		  {
		  case "chrome":cap.setBrowserName("chrome");break;
		  case "edge":cap.setBrowserName("edge");break;
		  case "firefox":cap.setBrowserName("firefox");break;

		  default: System.out.println("No matching browser");return;
	   }
		driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
	   }
	   if(p.getProperty("excution_env").equalsIgnoreCase("local"))
	   {
		   
	   switch(br.toLowerCase())
	   {
	   case "chrome":driver=new ChromeDriver();break;
	   case "edge":
		   WebDriverManager.edgedriver().setup();
		   driver=new EdgeDriver();break;
	   case "firefox":driver=new FirefoxDriver();break;
	   default :System.out.print("Invalid browser name..");return;
	   }
	   }
	   logger.info("**** launching Browser  ****");
	   driver.manage().deleteAllCookies();
	   driver.get(p.getProperty("appURL2"));//reading url from property file
	   logger.info("**** application launched ****");
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	   driver.manage().window().maximize();
	 }
	 @AfterClass(groups= {"sanity","Regression","Master"})
	 public void tierdown()
	 {
	 	driver.close();
	 }
	 public String randomstring()
	 {
	 	String randmstr= RandomStringUtils.randomAlphabetic(7);
	 	return randmstr;
	 }
	 public String randomAlphaNumeric()
	 {
	 	String randmstr= RandomStringUtils.randomAlphanumeric(8);
	 	return randmstr;
	 }
	 public String randomNumber()
	 {
	 	String randmstr= RandomStringUtils.randomNumeric(10);
	 	return randmstr;
	 }
	 public String captureScreen(String tname) {
			
			String timeStamp=new SimpleDateFormat("yyyMMddhhmmss").format(new Date());
			
			TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
			File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
			String tagetFilePath=System.getProperty("user.dir")+"\\screenShot\\"+ tname + "_"+ timeStamp +".png";
			File tagetFile=new File(tagetFilePath);
			
			sourceFile.renameTo(tagetFile);
			
			return tagetFilePath;
			
		}
	 
}
