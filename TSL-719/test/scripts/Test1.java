package scripts;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Test1 {

	public static void main(String[] args) {
		
		File pathToBinary = new File("C:\\Users\\AM101_PC15\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();       
		WebDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);
		driver.get("https://www.google.co.in");
		driver.findElement(By.id("lst-ib")).sendKeys("Selenium WebDriver");
		driver.findElement(By.name("btnK")).click();
		
		
		
		
		driver.get("https://www.wikipedia.org/");
		driver.findElement(By.id("js-link-box-en")).click();
		driver.findElement(By.name("search")).sendKeys("Selenium software");;
		driver.findElement(By.id("searchButton")).click();
		driver.findElement(By.linkText("Selenium (software)")).click();
		String title = driver.getTitle();
		System.out.println(title);
		String str2=driver.findElement(By.id("firstHeading")).getText();
		System.out.println(str2);

	}

}
