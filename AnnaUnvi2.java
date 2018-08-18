package scripts;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class AnnaUnvi2 {

	public static void main(String[] args) throws InterruptedException, IOException {

		File pathToBinary = new File("C:\\Users\\AM101_PC14\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();       
		WebDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);
		
		driver.get("https://annauniv.edu/");
		driver.manage().window().maximize();
		
		By a=By.xpath("/html/body/table/tbody/tr[1]/td[1]/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td[5]/div/a"); 
		driver.findElement(a).click();		
	
		Actions action = new Actions(driver);

		WebElement we = driver.findElement(By.xpath("//a[@id='link3']/strong"));

		WebElement we2= driver.findElement(By.id("menuItemHilite33"));

		action.moveToElement(we);

		Thread.sleep(1000);

		action.moveToElement(we2);

		action.build().perform();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("D:\\APT-TSL\\screenshot.png"));
		Thread.sleep(2000);

		action.click().perform();
		Thread.sleep(1000);
		driver.close();

	}

}
