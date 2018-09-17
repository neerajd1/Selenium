package scripts;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GoogleMaps {
	WebDriver driver;
	String search = "";
	String title = "";
	String phoneNo = "022 2279 5500";

	@BeforeTest
	public void setUp() {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC14\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinary, firefoxProfile);

		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void checkPrice() throws IOException {
		driver.get("http://www.google.com/maps/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//*[@id='searchboxinput']")).sendKeys(
				"Wankhede" + Keys.ENTER);

		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(
				"test\\resources\\screenshot\\GoogleMaps_SS1.png"));
		System.out.println("************************************");
		search = driver
				.findElement(
						By.xpath("//*[@id='pane']/div/div[1]/div/div/div[1]/div[3]/div[2]/div/div[2]/span[1]/span[1]/button"))
				.getText();

		if (isElementPresent(search)) {
			System.out.println("Stadium is present");
		} else {
			System.out.println("Stadium is not present");
		}

		title = driver.getTitle();

		if (title.equals("Wankhede Stadium - Google Maps")) {
			System.out.println("Title is perfect");
		} else {
			System.out.println("Title is not perfect");
		}

		System.out
				.println("Ratings: "
						+ driver.findElement(
								By.xpath("//*[@id='pane']/div/div[1]/div/div/div[1]/div[3]/div[2]/div/div[1]/span[1]/span/span"))
								.getText());

		System.out
				.println("Number of Reviews: "
						+ driver.findElement(
								By.xpath("//*[@id='pane']/div/div[1]/div/div/div[1]/div[3]/div[2]/div/div[1]/span[3]/ul/li/span[2]/span[1]/button"))
								.getText());

		search = driver
				.findElement(
						By.xpath("//*[@id='pane']/div/div[1]/div/div/div[7]/div/div[1]/span[3]/span[3]"))
				.getText();

		if (search.equals("mumbaicricket.com")) {
			System.out.println("mumbaicricket.com is present");
		} else {
			System.out.println("mumbaicricket.com is not present");
		}

		System.out
				.println("Address: "
						+ driver.findElement(
								By.xpath("//*[@id='pane']/div/div[1]/div/div/div[6]/div/div[1]/span[3]/span[3]"))
								.getText());
		
		if (isElementPresent(phoneNo)) {
			System.out.println("phoneNo. is present");
		} else {
			System.out.println("phoneNo. is not present");
		}
		
		scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(
				"test\\resources\\screenshot\\GoogleMaps_SS2.png"));
		System.out.println("************************************");
	}

	@AfterClass
	public void close() {
		driver.close();
	}

	public boolean isElementPresent(String value) {
		if (driver.getPageSource().contains(value)) {
			return true;
		} else {
			return false;
		}
	}
}
