package pageObjects;



import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.WaitHelper;


public class HomePage {
	public WebDriver ldriver;
	
	public WaitHelper waitHelper;
	public HomePage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
		waitHelper=new WaitHelper(ldriver);
	}
	@FindBy(xpath="(//div[@class=\"oxd-sidepanel-body\"]//span)[3]/..")
	@CacheLookup
	WebElement leaveButton;

	@FindBy(xpath="//nav[@class=\"oxd-topbar-body-nav\"]//li[1]")
	@CacheLookup
	WebElement applyLeaveNavButton;
	
	@FindBy(xpath="//div[@class=\"oxd-select-wrapper\"]")
	@CacheLookup
	WebElement vacationType;
	
	@FindBy(xpath="//div[@class=\"oxd-select-text-input\"]")
	@CacheLookup
	WebElement selectedVacationTypeText;
	
	@FindBy (xpath="//div[@role=\"option\"]//span")
	@CacheLookup
	WebElement vacationTypeCan;
	
	@FindBy(xpath="//div[@class=\"oxd-input-group\"]//p")
	@CacheLookup
	WebElement leaveBalance;
	
	@FindBy(xpath="(//div[@class=\"oxd-date-wrapper\"])[1]//input")
	@CacheLookup
	WebElement fromDateInput;
	
	@FindBy(xpath="(//div[@class=\"oxd-date-wrapper\"])[2]//input")
	@CacheLookup
	WebElement toDateInput;
	@FindBy(css="div.oxd-form-actions button")
	@CacheLookup
	WebElement applyLeaveSubmitButton;
	
	@FindBy(xpath="/(//div[@class=\"oxd-date-wrapper\"])[1]")
	@CacheLookup
	WebElement fromDateCalender;

	@FindBy(xpath="/(//div[@class=\"oxd-date-wrapper\"])[1]")
	@CacheLookup
	WebElement toDateCalender;
	
	@FindBy(xpath="//div[@class=\"oxd-calendar-selector-year-selected\"]")
	@CacheLookup
	WebElement yearSelector;
	
	
	@FindBy(xpath="//div[@class=\"oxd-calendar-selector-month-selected\"]")
	@CacheLookup
	WebElement monthSelector;
	
	@FindBy(xpath="//ul[@class=\"oxd-calendar-dropdown\"]//li[]")
	@CacheLookup
	WebElement monthList;
	
	
	@FindBy(xpath="div[@class=\"oxd-calendar-date\"]")
	@CacheLookup
	List<WebElement> dateList;
	
	
	
	
	
	public void dateSelection(String stringdate) 
	{
		String year=stringdate.split("-")[0];
		String month=stringdate.split("-")[1];
		String date =stringdate.split("-")[3];
		
		
		//Select Year
		yearSelector.click();		
		WebElement yearList=ldriver.findElement(By.xpath("//div[@class=\"oxd-calendar-selector-year-selected\"]/following-sibling::ul/li[contains(text(),"+year+")]"));
		yearList.click();
		
		//Select month
		monthSelector.click();
		WebElement monthList=ldriver.findElement(By.xpath("//ul[@class=\\\"oxd-calendar-dropdown\\\"]//li["+month+"]"));
		monthList.click();
		for (int i=0;i<dateList.size();i++)
		{
			String k=dateList.get(i).getText();
			if (k.equals(date)) {
				WebElement dateElement=dateList.get(i);
				dateElement.click();
					
				
				
			}
		}
		
		
		
		
	}

	
	public String getTitle()
	
	{
		return ldriver.getTitle();
	}
	public void clickOnLeavebutton()
	
	{
		waitHelper.waitForElement(leaveButton, 20);
		leaveButton.click();
	}
	public void clickApplyButton() {
		waitHelper.waitForElement(applyLeaveNavButton, 20);
		applyLeaveNavButton.click();
		
		
	}
	
	
	public boolean  checkNoLeaveTypes() 
	{

		try {
			WebElement element = ldriver.findElement(By.xpath("//p[contains(normalize-space(),\"No Leave Types with Leave Balance\")]"));
			
			return false;
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			System.out.println("Element not found.");
			return true;
			}
		
		
	}
	public void clickOnvacationType() {
		waitHelper.waitForElement(vacationType, 20);
		vacationType.click();
	}
	public String getSelectedVacationType()
	{
		waitHelper.waitForElement(selectedVacationTypeText, 20);
		return selectedVacationTypeText.getText();
	}
	public void selectVacationType()
	{
		waitHelper.waitForElement(vacationTypeCan, 20);
		vacationTypeCan.click();
	}
	
	public Double getLeaveBalance()
	{
		String availableleaveBalance=leaveBalance.getText();
		String numberPart=availableleaveBalance.split(" ")[0];
		Double days=Double.parseDouble(numberPart);
		return  days;	
		
	}
	public void clickOnApplyLeaveSubmitButton()
	{
		applyLeaveSubmitButton.click();
		try {
		    Thread.sleep(10000); // 3000 milliseconds = 3 seconds
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	}


}
