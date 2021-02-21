package page;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Pagelocator;
import pageUtils.BaseUtil;

public class UpdateHotelSearchResult extends BaseUtil{
	

	WebDriverWait wait;
	Pagelocator pagelocator = new Pagelocator();
	/*
	 *This Method is to validate the Search Result based on input 
	 * 
	 */
	@Test
	public void validateSearchResult() throws InterruptedException, IOException {
		
		String destination = readData("destval");
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(pagelocator.getChangeLocationBtn()));
		String summaryDest = driver.findElement(pagelocator.getSummaryDestination()).getText();
		Assert.assertEquals(destination, summaryDest, "Destination Not matching");
		
	}
	/*
	 *This Method is to update the location from Change location Panel
	 * 
	 */
	
	@Test(dependsOnMethods = "validateSearchResult")
	public void updateLocation() throws InterruptedException, IOException {
		
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(pagelocator.getChangeLocationBtn()));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement ele = driver.findElement(pagelocator.getChangeLocationBtn());
		js.executeScript("arguments[0].click();", ele);
	
		WebElement destInput = driver.findElement(pagelocator.getDest());
		destInput.clear();
		//update the location
		enterDestination(pagelocator.getDest(),readData("updatedDestVal"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(pagelocator.getSuggestion()));
		List<WebElement> list = driver.findElements(pagelocator.getSuggestion());
		
		for (int i = 0; i < list.size(); i++) {

			String val = list.get(i).getText();
			if (val.trim().equalsIgnoreCase(readData("updatedDestVal"))) {

				System.out.println("Value is" + val);
				list.get(i).click();
				break;
			}

		}
		wait.until(ExpectedConditions.elementToBeClickable(pagelocator.getFindMyHotelButton()));
		WebElement updateButton = driver.findElement(pagelocator.getFindMyHotelButton());
		js.executeScript("arguments[0].click();", updateButton);
		Thread.sleep(3000);
		waitUntilLoadingFinish();
		waitUntilSearchResultLoaded();
	}
	/*
	 *This Method is to validate the Search Result updated input 
	 * 
	 */
	
	@Test(dependsOnMethods = "updateLocation")
	public void validateUpdatedSearchResult() throws InterruptedException, IOException {
		
		String destination = readData("updatedDestVal");
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(pagelocator.getChangeLocationBtn()));
		String summaryDest = driver.findElement(pagelocator.getSummaryDestination()).getText();
		Assert.assertEquals(destination, summaryDest, "Destination Not matching after updation");
		
	}
}
