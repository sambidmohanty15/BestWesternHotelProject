package page;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.Pagelocator;
import pageUtils.BaseUtil;

public class HotelSearch extends BaseUtil {


	Pagelocator pagelocator = new Pagelocator();
	WebDriverWait wait;

	@BeforeTest
	public void initiateBrowser() throws InterruptedException, IOException {

		testSetup();
		validateHomePage();

	}

	@AfterTest
	public void quitSetUp() {

		closeBrowserSession();

	}
	
	/*
	 *This Method is insert the destination
	 * 
	 */

	@Test(description = "This method validates the Search functionality")
	public void destinationSetup() throws InterruptedException, IOException {
		System.out.println("Inside homepage");
		enterDestination(pagelocator.getDest(),readData("destval"));
		Thread.sleep(5000);
		List<WebElement> list = driver.findElements(pagelocator.getSuggestion());
		for (int i = 0; i < list.size(); i++) {

			String val = list.get(i).getText();
			if (val.trim().equalsIgnoreCase(readData("destval"))) {

				System.out.println("Value is" + val);
				list.get(i).click();
				break;
			}

		}

	}
	/*
	 *This Method is to give input as checkin and checkout date
	 * 
	 */

	@Test(dependsOnMethods = "destinationSetup")
	public void checkinDateSetUp() throws InterruptedException, IOException {

		wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(pagelocator.getCheckin()));
		clickElement(pagelocator.getCheckin());
		wait.until(ExpectedConditions.visibilityOfElementLocated(pagelocator.getDatePickerBox()));
		String startDate = readData("checkinDate");
        System.out.println("checkin date is :"+startDate);
		setCheckinCheckoutDate(startDate);
		wait.until(ExpectedConditions.visibilityOfElementLocated(pagelocator.getCheckOutBox()));
		System.out.println("Click checkout box");
		clickElement(pagelocator.getCheckOutBox());
		wait.until(ExpectedConditions.visibilityOfElementLocated(pagelocator.getDatePickerBox()));
		String endDate = readData("checkoutDate");
		System.out.println("checkout date is :"+endDate);
		setCheckinCheckoutDate(endDate);
		Thread.sleep(3000);

	}
	/*
	 *This Method is to click the hotel search button 
	 * 
	 */

	@Test(dependsOnMethods = "checkinDateSetUp")

	public void hotelSearchResult() throws InterruptedException, IOException {
		validateFindMyHotelButton();
		wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(pagelocator.getFindMyHotelButton()));
		submitSearchResult();
		waitUntilLoadingFinish();
		waitUntilSearchResultLoaded();
	}
	

}
