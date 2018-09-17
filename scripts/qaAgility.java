package scripts;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class qaAgility {
	WebDriver driver;
	int width;
	int height;

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
		driver.get("http://www.qaagility.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		System.out.println("*****************************");

		if (driver.getTitle().contains("QAAgility"))
			System.out.println("Title contains QAAgility in it");
		else
			System.out.println("Title doesn't contains QAAgility in it");

		width = driver.findElement(By.xpath("// *[@id='main_header']/a"))
				.getSize().getWidth();
		height = driver.findElement(By.xpath("// *[@id='main_header']/a"))
				.getSize().getHeight();

		System.out.println("Size of the Logo: \nHeight: " + height
				+ "  Width: " + width);
		System.out.println(driver.findElement(
				By.xpath("//*[@id='main_header']/nav/div/ul/li[2]/a"))
				.getAttribute("href"));

		assertTrue(driver
				.findElement(
						By.xpath("//*[@id='main_header']/nav/div/ul/li[2]/a"))
				.getAttribute("href").equals("https://twitter.com/qaagility"));
		
		System.out.println("Twitter Asserted");
		
		
		
		assertTrue(driver
				.findElement(
						By.xpath("/html/body/div[1]/div/div/footer/div/nav/ul/li/span"))
				.getText().equals("QAAgility Technologies Pvt. Ltd. © 2015. All Rights Reserved."));
		
		System.out.println("Footer Asserted");
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
