package scripts;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class alerts {

	public static void main(String[] args) throws InterruptedException {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC14\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");
		
		By simpleAlertLocator = By.xpath("//*[@id='content']/div/ul/li[1]/button");
		WebElement simpleAlertLocatorWebElement = driver.findElement(simpleAlertLocator);
		simpleAlertLocatorWebElement.click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		
		By confirmationAlertLocator = By.xpath("//*[@id='content']/div/ul/li[2]/button");
		WebElement conformationAlertLocatorWebElement = driver.findElement(confirmationAlertLocator);
		conformationAlertLocatorWebElement.click();
		Thread.sleep(2000);
		driver.switchTo().alert().dismiss();
		Thread.sleep(2000);
		
		By promptAlertLocator = By.xpath("//*[@id='content']/div/ul/li[3]/button");
		WebElement promptAlertLocatorWebElement = driver.findElement(promptAlertLocator);
		promptAlertLocatorWebElement.click();
		Thread.sleep(2000);
		driver.switchTo().alert().sendKeys("Prompt Alert");
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		
		driver.close();
	}

}
