package scripts;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Vodafone {
	public static void main(String[] args) {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC16\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
		Actions action = new Actions(driver);

		driver.get("https://www.vodafone.in");
		WebElement element = driver.findElement(By
				.xpath("//*[@id='ctl00_TM_liLocator']/a"));
		element.click();
		WebElement e1 = driver.findElement(By.name("ctl00$CU$ddlCircle"));
		e1.click();
		Select dropdown = new Select(driver.findElement(By
				.name("ctl00$CU$ddlCircle")));
		List<WebElement> list = dropdown.getOptions();

		for (int i = 1; i < list.size(); i++) {
			System.out.println("DropDown Menu: " + list.get(i).getText());
		}

		dropdown.selectByValue("haryana");
		System.out.println("haryana selected");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.navigate().back();
		System.out.println("haryana exit");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dropdown = new Select(driver.findElement(By.name("ctl00$CU$ddlCircle")));
		dropdown.selectByValue("chennai");
		System.out.println("chennai selected");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.navigate().back();
		System.out.println("chennai exit");
		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dropdown = new Select(driver.findElement(By.name("ctl00$CU$ddlCircle")));
		dropdown.selectByValue("mumbai");
		System.out.println("mumbai selected");
		
		/*try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//driver.navigate().back();
		System.out.println("mumbai exit");*/
		
	}
}
