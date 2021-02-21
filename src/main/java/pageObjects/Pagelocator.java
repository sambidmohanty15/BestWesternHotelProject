package pageObjects;

import org.openqa.selenium.By;

public class Pagelocator {

	By dest = By.xpath("//input[@id='destination-input']");
	By suggestionList = By.xpath("//ul[@id='google-suggestions' and @class='list-unstyled']/li");
	By checkin = By.id("checkin");
	By checkoutbox = By.id("checkout");
	By checkindate = By.xpath("//div[@id='ui-datepicker-div']/div/table/tbody/tr/td/a");
	By titleMonth = By.xpath("//div[@class='ui-datepicker-title']/span[1]");
	By titleYear = By.xpath("//div[@class='ui-datepicker-title']/span[2]");
	By nextBtn = By.xpath("//a[@title='Next']");
	By cookie = By.id("onetrust-accept-btn-handler");
	By findHotel = By.xpath("//div[@class='submitButtons']/button");
	By logo = By.id("img-nav-logo");
	By loading = By.cssSelector("div[class='cssload-loader']");
	By findMyHotelButton = By.xpath("//button[@id='btn-modify-stay-update']");
	By datepickerBox = By.id("ui-datepicker-div");
	
	By changeLocation = By.xpath("//button[@id='btn-modify-stay']");
	By summarydestination = By.id("summary-destination");
	By searchResultContent = By.xpath("//div[@class='searchResultsContainer']");
	
	
	public By getSearchResultContent() {

		return searchResultContent;
	}
	
	public By getSummaryDestination() {

		return summarydestination;
	}
	
	public By getChangeLocationBtn() {

		return changeLocation;
	}
	
	public By getDatePickerBox() {

		return datepickerBox;
	}
		
	public By getCheckOutBox() {

		return checkoutbox;
	}
	public By getFindMyHotelButton() {

		return findMyHotelButton;
	}
	
	public By waitLoading() {

		return loading;
	}
	
	public By getLogoName() {

		return logo;
	}

	public By getDest() {

		return dest;
	}

	public By getSuggestion() {

		return suggestionList;
	}
	public By getCheckinDate() {

		return checkindate;
	}
	public By getCheckin() {

		return checkin;
	}
	public By getTitleMonth() {

		return titleMonth;
	}
	public By getTitleYear() {

		return titleYear;
	}
	public By getNextButton() {

		return nextBtn;
	}
	public By getCookie() {

		return cookie;
	}
	public By getfindHotel() {

		return findHotel;
	}

}
