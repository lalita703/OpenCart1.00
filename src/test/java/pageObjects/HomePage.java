package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
 
public class HomePage extends Base_page
{
	public HomePage(WebDriver driver) {
		super(driver);
 	}
	@FindBy(xpath = "//span[text()='My Account']")
	 WebElement lnkaccount;
	@FindBy(xpath = "//a[text()='Register']")
	WebElement lnkregister;
	@FindBy(xpath = "//a[text()='Login']")
	WebElement lnkLogin;
	
	public void clickmyaccount()
	{
		lnkaccount.click();
	}
	public void clickRegister()
	{
		lnkregister.click();
	}
	public void clickLogin()
	{
		lnkLogin.click();
	}
	
  
	  
 }

