package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationpage extends Base_page
{
  public AccountRegistrationpage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txtfirstname;
    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtlasttname;
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtemail;
    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement txttelephone;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtpassword;
    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement txtconfirmpwd;
    @FindBy(xpath = "//input[@name=\"agree\"]")
    WebElement privacypolicy;
    @FindBy(xpath = "//input[@value=\"Continue\"]")
    WebElement btnContinue;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgaccountsucess;
    
    
    public void setfirstname(String name)
    {
    	txtfirstname.sendKeys(name);
    }
    public void setlastname(String name)
    {
    	txtlasttname.sendKeys(name);
    }
    public void setemail(String email)
    {
    	txtemail.sendKeys(email);
    }
    public void settelephone(String telephone)
    {
    	txttelephone.sendKeys(telephone);
    }
    public void setpassword(String pwd)
    {
    	txtpassword.sendKeys(pwd);
    }
    public void setconfrimpassword(String pwd)
    {
    	txtconfirmpwd.sendKeys(pwd);
    }
    public void clickprivacypolicy()
    {
    	privacypolicy.click();
    }
    public void clickContinue()
    {
    	btnContinue.click();
    }
    public String getaccountmsg()
    {
    	return msgaccountsucess.getText();
//    	try {
//        	return msgaccountsucess.getText();
//        	}
//        	catch(Exception e){
//        		return ("Resgistration Failed");
//        	}
    }
}