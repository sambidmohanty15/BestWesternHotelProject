package pageUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.Pagelocator;

public class BaseUtil {
	
	

	public static WebDriver driver;
	WebDriverWait wait;
	Properties prop;
	Pagelocator pagelocator = new Pagelocator();
	
	public void testSetup() throws InterruptedException, IOException
	{
	
	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\driver\\chromeDriver.exe");
	driver=new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	driver.get(readData("baseurl"));
	System.out.println("We are currently on the following URL" +driver.getCurrentUrl());
	
	}
	
	
	public String readData(String str) throws InterruptedException, IOException
	{

	FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\main\\resource\\commonUtils\\userdata.properties");
	
	prop = new Properties();
	prop.load(file);
	String strobject = prop.getProperty(str);
	return strobject;
	
	}
	
	public void waitUntilLoadingFinish() {
		
	    wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(pagelocator.waitLoading()));
		
	}
	
    public void waitUntilSearchResultLoaded() {
		
	  //  wait = new WebDriverWait(driver, 10);
    	//wait.until(ExpectedConditions.visibilityOfElementLocated(pagelocator.getSearchResultContent()));
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	public void validateHomePage() throws InterruptedException, IOException {
		
		WebElement ele = driver.findElement(pagelocator.getCookie());
		if (ele.isDisplayed()) {
			ele.click();
		}
		String expectedLogoName = readData("homepagetitle");
		String actualLogoName = driver.findElement(pagelocator.getLogoName()).getAttribute("alt");
		System.out.println(actualLogoName);
		Assert.assertEquals(expectedLogoName, actualLogoName,"LogoName not matching");
	}
   public void validateFindMyHotelButton() throws InterruptedException, IOException {
		
		String expectedButtonName = readData("findHotelButton");
		String actualButtonName = driver.findElement(pagelocator.getFindMyHotelButton()).getText();
		System.out.println(actualButtonName);
		Assert.assertEquals(expectedButtonName, actualButtonName,"Button Name not matching");
	}
   
   public void closeBrowserSession() {
	   
	   driver.manage().deleteAllCookies();
	   driver.quit();
   }
   
   public void clickElement(By by) {
	   
	   WebElement ele = driver.findElement(by);
	   ele.click();
   }
   
   public void enterDestination(By by,String keysToSend) {
	   
	   WebElement ele = driver.findElement(by);
	   ele.sendKeys(keysToSend);
   }
   
   public void submitSearchResult() throws InterruptedException {
	   
	    JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement ele = driver.findElement(pagelocator.getFindMyHotelButton());
		js.executeScript("arguments[0].click();", ele);
		Thread.sleep(3000);
   }
   
   public void setCheckinCheckoutDate(String date) throws InterruptedException, IOException {

		
		String splitter[] = date.split("-");
		String select_month = splitter[1];
		String select_day = splitter[0]; 
		List<WebElement> elements = driver.findElements(pagelocator.getTitleMonth());
		
		for (int i=0; i<elements.size();i++)
		{
		//Selecting the month from calendar
		if(elements.get(i).getText().trim().equalsIgnoreCase(select_month))
		{ 

		//Selecting the date 
		List<WebElement> days = driver.findElements(pagelocator.getCheckinDate());
		for (int n=0; n<days.size();n++)
		{ 
		String month = days.get(n).getAttribute("aria-label");
		if(days.get(n).getText().equals(select_day) && month.contains(select_month))
		{
		days.get(n).click();
		Thread.sleep(4000);
		return;
		}
		
		}

		}

		
		}
	   
   }
	
	
	
	
	


}
