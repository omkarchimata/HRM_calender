package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.WaitHelper;

public class AdminPage {
	public WebDriver ldriver;
	public WaitHelper waitHelper; 
	
	public AdminPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
		waitHelper=new WaitHelper(ldriver);
	}
	
	@FindBy(xpath="//input[@name=\"username\"]")
	@CacheLookup
	WebElement userNameField;
	@FindBy(xpath="//input[@name=\"password\"]")
	@CacheLookup
	WebElement passwordField;
	

	@FindBy(xpath="//button[@type=\"submit\"]")
	@CacheLookup
	WebElement loginButton;

	
	
	
	public void enterUserName()
	{
		waitHelper.waitForElement(userNameField, 20);
		userNameField.sendKeys("Admin");
	}
	public void enterPassword()
	{
		waitHelper.waitForElement(passwordField, 20);
		passwordField.sendKeys("admin123");
	}
	public void clickLoginButton() {
		waitHelper.waitForElement(loginButton, 20);
		loginButton.click();
		
	}
	
	
	

}
