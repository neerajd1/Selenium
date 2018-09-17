package scripts;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Britannica {
	WebDriver driver;

	@Before
	public void setUp() {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC14\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinary, firefoxProfile);

		driver.get("https://www.britannica.com/");
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws IOException, InterruptedException {
		
		
		driver.findElement(By.name("query")).sendKeys("bit");
		Thread.sleep(7000);
		File scrFile = ((TakesScreenshot) driver)
		.getScreenshotAs(OutputType.FILE);
		try {
		FileUtils.copyFile(scrFile, new File(
		"D:\\workspace-selenium_maven_jenkins\\MockNeeraj\\test\\resources\\screenshots\\bits.png"));
		} catch (IOException e) {
		e.printStackTrace();
		}
		List<WebElement> lst = driver.findElements(By.className("ui-autocomplete--list-item"))/*.findElements(By.tagName("li"))*/;

		for(WebElement lst1:lst)
		{
		System.out.println(lst1.getText());
		if(lst1.getText().equals("Bitcoin: The Rise of Virtual Currency"))
		{
		lst1.click();
		System.out.println("Bitcoin clicked");
		break;
		}
		}
	}

	@After
	public void close() {

	}

}
