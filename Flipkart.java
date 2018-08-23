package DataDriven_eCommerce;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jxl.*;

public class Flipkart {
	int[] arr = new int[24];
	int[] arr1 = new int[24];

	private WebDriver driver;

	SoftAssert s_assert = new SoftAssert();

	@BeforeTest
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - Start Time - "
				+ new Timestamp(date.getTime()));

	}

	@DataProvider(name = "Cycle1")
	public Object[][] createData1() {
		Object[][] retObjArr = getTableArray(
				"test\\resources\\data\\book_data3.xls", "DataPool", "mobile");
		System.out.println("*****************  2 *************************");
		return (retObjArr);
	}

	@Test(dataProvider = "Cycle1")
	public void testUntitled(String brandName) throws Exception {

		driver.get("https://www.flipkart.com/");
		try {
			driver.findElement(By.xpath("//div[2]/div/div/button")).click();

			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		driver.findElement(By.xpath("//input[@name='q']")).clear();
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys(brandName);

		driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);
		driver.findElement(
				By.xpath("//*[@id='container']/div/div[1]/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/div[3]"))
				.click();
		System.out
				.println(driver
						.findElement(
								By.xpath("//*[@id='container']/div/div[1]/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/div[3]"))
						.getText());

		List<WebElement> mobile = driver
				.findElements(By
						.xpath("//*[@id='container']/div/div[1]/div[2]/div/div[1]/div[2]/div/div/div/div/a/div/div/div/div/div[1]"));
		for (int i = 0; i < mobile.size(); i++) {
			// System.out.println(mobile.size());

			arr[i] = Integer.parseInt(mobile.get(i).getText()
					.replaceAll(",", "").substring(1));
			arr1[i] = arr[i];

			System.out.println("Price of " + i + " Product :-" + arr[i] );
		}
		Arrays.sort(arr1);
		for (int i = 0; i < mobile.size(); i++) {
			if (arr[i] == arr1[i]) {
				System.out.println("Sorted");
			} else
				System.out.println("Unsorted");
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();

		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - End Time - "
				+ new Timestamp(date.getTime()));
	}

	public boolean verifyTextPresent(String value) {
		boolean x = driver.getPageSource().contains(value);
		return x;
	}

	public String[][] getTableArray(String xlFilePath, String sheetName,
			String tableName) {
		String[][] tabArray = null;
		try {
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			Sheet sheet = workbook.getSheet(sheetName);

			int startRow, startCol, endRow, endCol, ci, cj;

			Cell tableStart = sheet.findCell(tableName);
			startRow = tableStart.getRow();
			startCol = tableStart.getColumn();

			Cell tableEnd = sheet.findCell(tableName, startCol + 1,
					startRow + 1, 100, 64000, false);

			endRow = tableEnd.getRow();
			endCol = tableEnd.getColumn();

			System.out.println("startRow=" + startRow + ", endRow=" + endRow
					+ ", " + "startCol=" + startCol + ", endCol=" + endCol);
			tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
			ci = 0;

			for (int i = startRow + 1; i < endRow; i++, ci++) {
				cj = 0;
				for (int j = startCol + 1; j < endCol; j++, cj++) {
					tabArray[ci][cj] = sheet.getCell(j, i).getContents();
				}
			}
		} catch (Exception e) {
			System.out.println("error in getTableArray()");

		}

		return (tabArray);
	}

}