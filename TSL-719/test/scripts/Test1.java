package scripts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Test1 {

	public static void main(String[] args) throws IOException {

		File pathToBinary = new File(
				"C:\\Users\\AM101_PC15\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
		driver.get("https://www.google.co.in");
		driver.findElement(By.id("lst-ib")).sendKeys("Selenium WebDriver");
		driver.findElement(By.name("btnK")).click();

		driver.get("https://www.wikipedia.org/");
		driver.findElement(By.id("js-link-box-en")).click();
		driver.findElement(By.name("search")).sendKeys("Selenium software");
		;
		driver.findElement(By.id("searchButton")).click();
		driver.findElement(By.linkText("Selenium (software)")).click();
		String title = driver.getTitle();
		System.out.println("Title" + title);
		String heading = driver.findElement(By.id("firstHeading")).getText();
		System.out.println("Heading" + heading);
		String pageSource = driver.getPageSource();
		System.out.println("Page Source :- \n" + pageSource);
		byte[] strToBytes = pageSource.getBytes();
		FileOutputStream outputStream;
		outputStream = new FileOutputStream(
				"C:\\Users\\AM101_PC15\\Desktop\\pagesource.txt");

		outputStream.write(strToBytes);
		outputStream.close();
		driver.get("https://www.agiletestingalliance.org/");

		List<WebElement> allElements1 = driver.findElement(
				By.xpath("//footer/div[@class='soc-icons']")).findElements(
				By.tagName("a"));

		for (WebElement we : allElements1) {
			System.out.println(we.getAttribute("href"));
		}

		driver.get("https://www.google.co.in/");
		System.out.println(driver.findElement(By.id("lst-ib")).getAttribute(
				"title"));

		driver.get("https://www.google.co.in/");
		System.out.println(driver.findElement(
				By.xpath("//div[@id='hplogo']/a/img")).getAttribute("title"));

		driver.manage().window().maximize();
		Dimension n = new Dimension(592, 592);
		driver.manage().window().setSize(n);

		driver.navigate().back();
		driver.navigate().refresh();
		driver.navigate().to("guru99");
		driver.navigate().forward();
		driver.manage().window().maximize();
		
	}

}
