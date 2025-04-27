package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {
	
	
	public WebDriver driver;
	public WaitHelper(WebDriver driver) {
		this.driver=driver;
	}
	
	public void statciWait(Integer waitInMill)
	{
		try {
		    Thread.sleep(waitInMill); // 3000 milliseconds = 3 seconds
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	}

	public void waitForElement(WebElement element,long timeOutInSeconds)
	{
		WebDriverWait wait=new WebDriverWait(driver,timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void waitForElementToClickble(WebElement element,long timeOutInSeconds)
	{
		WebDriverWait wait=new WebDriverWait(driver,timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public void waitForinvisibilityOfElementLocated(WebElement element,long timeOutInSeconds) {
	WebDriverWait wait=new WebDriverWait(driver,timeOutInSeconds);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".oxd-form-loader")));
}
}
