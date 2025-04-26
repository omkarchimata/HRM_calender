package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;
//import junit.framework.Assert;
import org.junit.Assert;
import pageObjects.AdminPage;
import pageObjects.HomePage;

public class Steps {
	
	
	public WebDriver driver;
	public AdminPage admin;
	public HomePage home;
	
	@Given("I open browser")
	public void i_open_browser() {
	    // Write code here that turns the phrase above into concrete actions
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//drivers/chromedriver.exe");  
		driver=new ChromeDriver();
		admin=new AdminPage(driver);
	}

	@Given("open the admin page")
	public void open_the_admin_page() {
	     driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@When("I enter username and password")
	public void i_enter_username_and_password() {
	    // Write code here that turns the phrase above into concrete actions
	    admin.enterUserName();
	    admin.enterPassword();
	}

	@When("I click on Login button")
	public void i_click_on_login_button() {
	    // Write code here that turns the phrase above into concrete actions
	    admin.clickLoginButton();
	}

	@Then("validate home page title {string}")
	public void validate_home_page_title(String expectedTitle) {
	    // Write code here that turns the phrase above into concrete actions
	   // Assert.assertEquals(expectedTitle,home.getTitle() );
		home=new HomePage(driver);
		System.out.println(home.getTitle());
	}

	@When("I click on leave option")
	public void i_click_on_leave_option() {
	    // Write code here that turns the phrase above into concrete actions
	    	home.clickOnLeavebutton();
	}
	
	
	@When("I click on apply button")
	public void i_click_on_apply_button() {
	    // Write code here that turns the phrase above into concrete actions
		home.clickApplyButton();
	}
	@When ("validate leave types available")
	public void validate_leave_types_available()
	{
		System.out.println("the dufkj"+home.checkNoLeaveTypes());
		if (home.checkNoLeaveTypes()) {
			System.out.println("the leave types are not available"+home.checkNoLeaveTypes());
			
	    	Assert.fail("leave types is not available");
	    	System.out.println("the leave types are not available");
			
		}
		else {
			System.out.println("leave types not are avaialable");
		}
		
	}

	@When("I select leave type")
	public void i_select_leave_type() {
	    // Write code here that turns the phrase above into concrete actions
		home.clickOnvacationType();
		home.selectVacationType();
	    
	}

	@Then("check availabile leave count")
	public void check_availabile_leave_count() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(3000);
	    Double leaveBalance=home.getLeaveBalance();
	    System.out.println("the leave available is"+leaveBalance);
	    if (leaveBalance>0.5)
	    {
	    	Assert.assertTrue(true);
	    	
	    }else {
	    	Assert.fail("Leave balance is not available");
	    }
	    
	    
	}
	@When("I close browser")
	public void i_close_browser() {
	    // Write code here that turns the phrase above into concrete actions
	    driver.close();
	}
	@When ("I select From date {string}")
	public void I_select_From_date(String fromDate)
	{
		home.dateSelection(fromDate);
	}
	@When ("I select to date {string}")
	public void I_select_to_date(String toDate)
	
	{
		home.dateSelection(toDate);
	
	}
	@When("I click on apply leave submit button")
	public void I_click_on_apply_leave_submit_button()
	{
		home.clickOnApplyLeaveSubmitButton();
	}
	


}
