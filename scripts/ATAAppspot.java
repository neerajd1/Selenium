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

public class ATAAppspot {
	WebDriver driver;
	String fNumber,sNumber;

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
		driver.get("http://ata123456789123456789.appspot.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		System.out.println("*****************************");
		
		driver.findElement(By.xpath("//*[@id='gwt-uid-7']")).click();
		
		fNumber = "7";
		sNumber = "4";
		
		int fNum = 7;//Integer.parseInt(fNumber);
		int sNum = 4;//Integer.parseInt(sNumber);
		
		driver.findElement(By.xpath("//*[@id='ID_nameField1']")).clear();
		driver.findElement(By.xpath("//*[@id='ID_nameField1']")).sendKeys(fNumber);
		driver.findElement(By.xpath("//*[@id='ID_nameField2']")).clear();
		driver.findElement(By.xpath("//*[@id='ID_nameField2']")).sendKeys(sNumber);
		
		int result = (fNum*fNum) - (2*(fNum*sNum)) + (sNum*sNum);
		String STRresult = Integer.toString(result);
		
		
		driver.findElement(By.xpath("//*[@id='ID_calculator']")).click();
	
		assertTrue(driver.findElement(By.xpath("//*[@id='ID_nameField3']")).getAttribute("value").equals(STRresult));
		System.out.println("Results Matched!!!!");

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
