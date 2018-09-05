package scripts;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class POITemplate {

	SoftAssert s_assert;
	WebDriver driver;

	@SuppressWarnings("deprecation")
	public static String[][] getExcelData(String fileName, String sheetName)
			throws IOException {
		String[][] arrayExcelData = null;
		Workbook wb = null;
		try {
			File file = new File(fileName);
			FileInputStream fs = new FileInputStream(file);
			// .xls
			if (fileName.substring(fileName.indexOf(".")).equals(".xlsx")) {

				// If it is xlsx file then create object of XSSFWorkbook class

				wb = new XSSFWorkbook(fs);
			} else if (fileName.substring(fileName.indexOf(".")).equals(".xls")) {
				// If it is xls file then create object of HSSFWorkbook class
				wb = new HSSFWorkbook(fs);
			}
			Sheet sh = wb.getSheet(sheetName);

			int totalNoOfRows = sh.getPhysicalNumberOfRows();
			int totalNoOfCols = sh.getRow(0).getPhysicalNumberOfCells();

			System.out.println("totalNoOfRows=" + totalNoOfRows + ","
					+ " totalNoOfCols=" + totalNoOfCols);
			arrayExcelData = new String[totalNoOfRows - 1][totalNoOfCols];
			for (int i = 1; i <= totalNoOfRows - 1; i++) {
				for (int j = 0; j <= totalNoOfCols - 1; j++) {
					sh.getRow(i).getCell(j).setCellType(1);
					arrayExcelData[i - 1][j] = sh.getRow(i).getCell(j)
							.getStringCellValue().toString();
				}
			}
		} catch (Exception e) {
			System.out.println("error in getExcelData()");
		}
		return arrayExcelData;
	}

	@BeforeTest
	public void setUp() throws Exception {

		System.out.println("*****************  1 *************************");
		System.setProperty("webdriver.chrome.driver",
				"test\\resources\\drivers\\chromedriver.exe");
		// driver = new FirefoxDriver();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - Start Time - "
				+ new Timestamp(date.getTime()));
	}

	@DataProvider(name = "DP1")
	public Object[][] createData1() throws IOException {
		Object[][] retObjArr = getExcelData(
				"test\\resources\\data\\movie_data_POI.xls", "DataPool");
		System.out.println("*****************  2 *************************");
		return (retObjArr);
	}

	@Test(dataProvider = "DP1")
	public void testDataProviderExample(String movieTitle, String directorName,
			String moviePlot, String actorName) throws Exception {
		s_assert = new SoftAssert();
		System.out.println("*****************  3 *************************");
		driver.get("http://www.imdb.com/");
		System.out.println("*****************  3.1 *************************");
		driver.findElement(By.id("navbar-query")).sendKeys(movieTitle);
		driver.findElement(By.id("navbar-submit-button")).click();

		/*
		 * for (int second = 0;; second++) { if (second >= 60) fail("timeout");
		 * try { if (isElementPresent(By.linkText(movieTitle))) break; } catch
		 * (Exception e) {} Thread.sleep(1000); }
		 */

		driver.findElement(By.linkText(movieTitle)).click();

		/*
		 * for (int second = 0;; second++) { if (second >= 60) fail("timeout");
		 * try { if (isElementPresent(By.xpath(
		 * "xpath=/html/body[@id='styleguide-v2']/div[@id='wrapper']/div[@id='root']/div[@id='pagecontent']/div[@id='content-2-wide']/div[@id='maindetails_center_top']/div/div/table[@id='title-overview-widget-layout']/tbody/tr[1]/td[@id='overview-top']/div[6]/a[3]"
		 * ))) break; } catch (Exception e) {} Thread.sleep(1000); }
		 */

		if (verifyTextPresent(directorName))
			System.out.println("Found Dir Name: " + directorName);
		s_assert.assertTrue(verifyTextPresent(directorName));

		if (verifyTextPresent(moviePlot))
			System.out.println("Found Movie Plot: " + moviePlot);
		s_assert.assertTrue(verifyTextPresent(moviePlot));

		if (verifyTextPresent(actorName))
			System.out.println("Found Actor Name: " + actorName);
		s_assert.assertTrue(verifyTextPresent(actorName));

		s_assert.assertAll();

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

	private boolean isElementPresent(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}