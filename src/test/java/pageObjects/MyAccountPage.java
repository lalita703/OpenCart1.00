package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends Base_page
{ 
	public MyAccountPage(WebDriver driver) {
		super(driver);
}
	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement msgHeading;
	
	@FindBy(xpath = "(//a[text()='Logout'])[2]")
	WebElement lnklogout;
	
	
	
	public boolean isMyAccountPageExists()
	{
		try
		{
		return (msgHeading.isDisplayed());
        }
		catch(Exception e)
		{
			return false;
		}
} 
	public void clicklogout()
	{
		lnklogout.click();
	}

}