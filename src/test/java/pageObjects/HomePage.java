package pageObjects;



import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	
	
	@FindBy(xpath="//div[@class=\"oxd-calendar-date\"]")
	@CacheLookup
	List<WebElement> dateList;
	
	@FindBy(xpath="(//i[@class=\"oxd-icon bi-calendar oxd-date-input-icon\"])[1]")
	@CacheLookup
	WebElement inputToGetCalender;
	//##########################################################
	//#######                           ####################################
	//#######               Holiday     ######################################
	//##########################################################
	//##########################################################
	
	
	
	@FindBy(css=".oxd-form-loader")
	@CacheLookup
	WebElement loaderForm;
	
	public void dateSelection(String stringdate) 
	{
		
		waitHelper.waitForinvisibilityOfElementLocated(loaderForm, 20);
		// Wait for loader to disappear

		Integer date=Integer.parseInt(stringdate.split("-")[0]);
		Integer month=Integer.parseInt(stringdate.split("-")[1]);
		Integer year =Integer.parseInt(stringdate.split("-")[2]);
		
		 Actions actions = new Actions(ldriver);
		System.out.println(year);
		System.out.println(month);
		System.out.println(date);
		waitHelper.waitForElementToClickble(inputToGetCalender, 20);
		inputToGetCalender.click();
		//Select Year
		waitHelper.waitForElement(yearSelector, 20);
		actions.moveToElement(yearSelector);
		waitHelper.statciWait(2000);
		
		yearSelector.click();		
		WebElement yearList=ldriver.findElement(By.xpath("//div[@class=\"oxd-calendar-selector-year-selected\"]/following-sibling::ul/li[contains(text(),"+year+")]"));
		yearList.click();
		
		//Select month
		waitHelper.waitForElement(monthSelector, 20);
		
		monthSelector.click();
		waitHelper.statciWait(2000);
		WebElement monthList=ldriver.findElement(By.xpath("//ul[@class='oxd-calendar-dropdown']//li["+(month)+"]"));
		waitHelper.waitForElement(monthList, 20);
		
		monthList.click();

	        actions.moveToElement(monthList).perform();
	        waitHelper.statciWait(2000);
		System.out.println("The date list is "+dateList.size());
		for (int i=0;i<dateList.size();i++)
		{
			String k=dateList.get(i).getText();
			System.out.println("the K is "+dateList.get(i).getText());
			if (Integer.parseInt(k) == date) {
				WebElement dateElement=dateList.get(i);
				System.out.println("enther to for loop");
				waitHelper.waitForElement(dateElement, 20);
		        actions.moveToElement(dateElement).perform();
		        waitHelper.statciWait(2000);
				dateElement.click();
				break;
				
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
		waitHelper.statciWait(2000);
	}

	
	
	//#########################################################

	//##########################################################
		//#######                           ####################################
		//#######               Holiday     ######################################
		//##########################################################
		//##########################################################
		
		
		@FindBy(xpath="//nav[@class=\"oxd-topbar-body-nav\"]//li[5]")
		@CacheLookup
		WebElement configureNavButton;
		
		
		
		@FindBy(xpath="//nav[@class=\"oxd-topbar-body-nav\"]//li[5]/ul/li[4]")
		@CacheLookup
		WebElement holidayOfConfigureListButton;
		

		@FindBy(xpath="//div[@class=\"orangehrm-header-container\"]/button")
		@CacheLookup
		WebElement addButtonToAddHoliday;
		
		@FindBy(xpath="//div[@class=\"orangehrm-card-container\"]/h6")
		@CacheLookup
		WebElement textAddHoliday;
		

		@FindBy(xpath="(//div[@class=\"oxd-form-row\"][1]//input)[1]")
		@CacheLookup
		WebElement holidayName;
		
		@FindBy(xpath="(//div[@class=\"oxd-form-row\"][1]//input)[2]")
		@CacheLookup
		WebElement dateOfHolidayName;
		
		@FindBy(xpath="//div[@class=\"oxd-form-actions\"]//button[@type=\"submit\"]")
		@CacheLookup
		WebElement saveButtonToAddHoliday;
		
		
		public void clickOnConfigureButton() {
			waitHelper.waitForElement(configureNavButton, 20);
			
			configureNavButton.click();
		}
		
		public void selectHolidayOption() {
			waitHelper.waitForElement(holidayOfConfigureListButton, 20);
			holidayOfConfigureListButton.click();
			
		}
		
		public boolean validateAvaliabilityOfAddHolidayButton() {
			waitHelper.waitForElement(addButtonToAddHoliday, 20);
			return addButtonToAddHoliday.isDisplayed();
		}
		public void clickOnAddHolidayButton()
		{
			waitHelper.waitForElement(addButtonToAddHoliday, 20);
			addButtonToAddHoliday.click();
			
		}
		public boolean validateAvaliabilityOfAddHolidayText() {
			waitHelper.waitForElement(textAddHoliday, 20);
			return textAddHoliday.isDisplayed();
		}
		
		public void enterHolidayName(String name)
		{
			waitHelper.waitForElement(holidayName, 20);
			holidayName.sendKeys(name);
			
		}
		
		public void  saveAddHoliday()
		{
			waitHelper.waitForElement(saveButtonToAddHoliday, 20);
			saveButtonToAddHoliday.click();
		}
		
		
		
		
}
