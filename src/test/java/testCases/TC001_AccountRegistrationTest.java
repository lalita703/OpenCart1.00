package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationpage;
import pageObjects.HomePage;
import testBase.Baselibrary;

public class TC001_AccountRegistrationTest extends Baselibrary {

	@Test(groups={"Regression","Master"})
	public void verifyaccountregistration() {
		try {
			logger.info("**** Starting TC001_AccountRegistrationpage ****");
			HomePage hp = new HomePage(driver);
			hp.clickmyaccount();
			logger.info("clicked on my account Link ");
			hp.clickRegister();
			logger.info("clicked on Register Link ");

			AccountRegistrationpage regpage = new AccountRegistrationpage(driver);
			logger.info("providing customer details....");
			regpage.setfirstname(randomstring().toUpperCase());
			regpage.setlastname(randomstring().toUpperCase());
			regpage.setemail(randomstring() + "@gmail.com");
			regpage.settelephone(randomNumber());
			String pwd = randomAlphaNumeric();
			regpage.setpassword(pwd);
			regpage.setconfrimpassword(pwd);
			regpage.clickprivacypolicy();
			regpage.clickContinue();

			logger.info("Validating expected message");
			String confmsg = regpage.getaccountmsg();
//			 Assert.assertEquals(confmsg, "Your Account Has Been Created!","registration Failed");
			if (confmsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			} else {
				logger.error("Test failed..");
				logger.debug("Debug logs..");
				Assert.assertTrue(false);
			}

		} catch (Exception e) {

			Assert.fail();
		}
		logger.info("****Finished TC001_AccountRegistrationpage ****");

	}
}
