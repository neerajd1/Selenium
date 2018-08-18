package scripts;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;




public class Anna {

	public static void main(String[] args) {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC16\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
		Actions action = new Actions(driver);

		driver.get("https://www.annauniv.edu/");

		WebElement element = driver
				.findElement(By
						.xpath("/html/body/table/tbody/tr[1]/td[1]/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td[5]/div/a"));
		element.click();

		WebElement element1 = driver.findElement(By
				.xpath("//*[@id='link3']/strong"));

		WebElement element2 = driver.findElement(By.id("menuItem33"));

		action.moveToElement(element1).moveToElement(element2).build()
				.perform();
		//element2.click();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("D:\\workspace-selenium_maven_jenkins\\TSL-719\\test\\resources\\screenshots\\screenshot.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		

	}

}
