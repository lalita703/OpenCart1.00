package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.Baselibrary;

public class TC002_Login_test extends Baselibrary
{
	@Test(groups={"sanity","Master"})
  public void verify_Login()
  {  
	try
	{
	  logger.info("*****Starting TC_002_Logintest*****");
	  //HomePage
	  HomePage hp=new HomePage(driver);
	  hp.clickmyaccount();
	  	hp.clickLogin();
	  
	  //Login Page
	  LoginPage lp=new LoginPage(driver);

	  lp.setEmail(p.getProperty("email"));
	  logger.info("Enter Email in The Email text field");
	  lp.setpassword(p.getProperty("password"));
	  logger.info("Enter pass in The pass text field");
	  
	  lp.clickLogin();
	  logger.info("Click on the login btn");
	  //MyAccount Page
	  MyAccountPage macc=new MyAccountPage(driver);
	  boolean targetPage=macc.isMyAccountPageExists();
	  Assert.assertTrue(targetPage);//Assert.assertEquals(targetPage, true,"Login failed");

	}
	  catch(Exception e)
	  {
		 Assert.fail(); 
	  }
	  logger.info("*****Finished TC_002_Logintest*****");

  }
}

