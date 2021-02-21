package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class HotelTest {
	
	public static WebDriver driver;
	
	public void initilizeDriver() {
		
		//System.out.println(System.getProperty("user.dir"));
		
		System.setProperty("webdriver.chrome.driver","C:\\Driver\\chromeDriver.exe");
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.bestwestern.com/en_US.html");
	}

}
