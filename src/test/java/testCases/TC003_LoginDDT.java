package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.Baselibrary;
import utilitys.DataProviders;


public class TC003_LoginDDT extends Baselibrary
{
 @Test(dataProvider = "loginData", dataProviderClass=DataProviders.class,groups="Datadriven")//getting data provider from different class
 public void verfy_LoginDDT(String email,String pwd,String exp) throws InterruptedException
 {
	 logger.info("*****Starting TC_003_LogintDDT*****");

	 try
	 {
	// Home page
	 HomePage hp=new HomePage(driver);
	  hp.clickmyaccount();
	  logger.info("*****Click on my account*****");

	  hp.clickLogin();
	  
	  //Login Page
	  LoginPage lp=new LoginPage(driver);

	  lp.setEmail(email);
	  logger.info("Enter Email in The Email text field");
	  lp.setpassword(pwd);
	  logger.info("Enter pass in The pass text field");
	  
	  lp.clickLogin();
	  logger.info("Click on the login btn");
	  //MyAccount Page
	  MyAccountPage macc=new MyAccountPage(driver);
	  boolean targetPage=macc.isMyAccountPageExists();
	  
	  
	  /*Data is valid  -login success - test pass - logout
	  Data is valid --login failed -test fail

	  Data is invalid - login success - test fail - logout 
	  Data is invalid --login failed -test pass
	  */

	  if(exp.equalsIgnoreCase("valid"))
	  {
		 if(targetPage==true)
		 {
			 macc.clicklogout();
			 Assert.assertTrue(true);
		 }
		 else
		 {
			 Assert.assertTrue(false);
		 }
	  }
	  
	   if(exp.equalsIgnoreCase("invalid"))
	   {
		 if(targetPage==true)
		 { 
		  macc.clicklogout();
		  Assert.assertTrue(false);

	  }
		 else
		 {
			 Assert.assertTrue(true);
		 }
	   }
	 }
	   catch(Exception e)
	   {
		   Assert.fail();
		   Thread.sleep(3000);
	   }
		  logger.info("*****Finished TC_003_LoginDDT*****");

 }
 
 }

 