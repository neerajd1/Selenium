package scripts;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class AllMovie {
	WebDriver driver;
	String searchResults = "";

	@Before
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
		driver.get("http://www.allmovie.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(
				By.xpath("/html/body/div[2]/div[1]/div/div/div[2]/form/input[2]"))
				.sendKeys("The Godfather" + Keys.ENTER);

		searchResults = driver.findElement(
				By.xpath("//*[@id='cmn_wrap']/div[1]/div[2]/div[2]/h1"))
				.getText();
		searchResults = searchResults.replaceAll("[a-zA-Z]", "");
		System.out.println("Results : " + searchResults);

		driver.findElement(
				By.xpath("//*[@id='cmn_wrap']/div[1]/div[2]/div[2]/ul/li[1]/div[2]/div[1]"))
				.click();

		if (driver
				.findElement(
						By.xpath("//*[@id='cmn_wrap']/div[1]/div[2]/header/hgroup[2]/span[1]/a"))
				.getText().equals("Crime")) {
			System.out.println("Genre is Crime");
		} else {
			System.out.println("Genre is not Crime");
		}

		if (driver
				.findElement(
						By.xpath("//*[@id='cmn_wrap']/div[1]/div[2]/header/hgroup[2]/span[6]/span"))
				.getText().equals("R")) {
			System.out.println("MPAA Rating is R");
		} else {
			System.out.println("MPAA Rating is not R");
		}

		driver.findElement(
				By.xpath("//*[@id='cmn_wrap']/div[1]/div[2]/ul/li[3]/a"))
				.click();

		if (driver
				.findElement(By.xpath("//*[@id='movie-director-link']/span/a"))
				.getText().equals("Francis Ford Coppola")) {
			System.out.println("Francis Ford Coppola is the director");
		} else {
			System.out.println("Francis Ford Coppola is not the director");
		}

		if (driver
				.findElement(
						By.xpath("//*[@id='cmn_wrap']/div[1]/div[2]/section/div[2]/div[2]/div[3]"))
				.getText().equals("	Michael Corleone")) {
			System.out.println("Michael Corleone is played by Al Pacino");
		} else {
			System.out.println("Michael Corleone is not played by Al Pacino");
		}

	}

	@After
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
