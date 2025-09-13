package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.Baselibrary;

public class LoginPage extends Base_page 
{
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "//input[@placeholder='E-Mail Address']")
	WebElement textEmailAddress;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement textpassword;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnlogin;
	
	public void setEmail(String email)
	{
		textEmailAddress.sendKeys(email);
	}
	public void setpassword(String pwd)
	{
		textpassword.sendKeys(pwd);	
	}
	public void clickLogin()
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		  wait.until(ExpectedConditions.visibilityOf(btnlogin));
		btnlogin.click(); 
	}

	}


