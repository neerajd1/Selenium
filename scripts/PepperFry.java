package scripts;

import java.io.File;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PepperFry {
	WebDriver driver;
	String str = "";
	int[] sorted ;
	int[] unsorted ;

	@BeforeTest
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"test\\resources\\driver\\chromedriver(1).exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		 driver.manage().window().maximize();

		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - Start Time - "
				+ new Timestamp(date.getTime()));

	}

	@DataProvider(name = "DP2")
	public Object[][] createData2() {
		Object[][] retObjArr = getTableArray(
				"test\\resources\\data\\Book2.xls", "Sheet1", "delimiter");
		System.out.println("*****************  2 *************************");
		return (retObjArr);
	}

	@Test(dataProvider = "DP2")
	public void checkPrice(String search) throws InterruptedException {

		System.out
				.println("***************Test Case Started**********************");
		driver.get("http://www.pepperfry.com/");
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println("\tPepperfry.com loaded");

		try {
			driver.findElement(By.xpath("//*[@id='signinupPopupBox']/a"))
					.click();
		} catch (Exception e) {
		}
		driver.findElement(By.id("search")).clear();
		driver.findElement(By.id("search")).sendKeys(search + Keys.ENTER);
		System.out.println("\t"+search+" searched");

		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.cssSelector("#curSortType")))
				.click().perform();
		Thread.sleep(2000);
		System.out.println("\tDropdown selected");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='sortBY']/li[2]")).click();
		Thread.sleep(2000);
		System.out.println("\tLow To High set");

		List<WebElement> alist = driver.findElements(By
				.className("srch-rslt-cards"));
		
		System.out.println("\tResults :"+alist.size());
		
		sorted = new int[alist.size()];
		unsorted = new int[alist.size()];

		int j = 0;

		for (WebElement i : alist) {
			str = i.findElement(By.className("clip-offr-price")).getText();
			str = str.substring(3);
			str = str.replaceAll(",", "");
			sorted[j] = Integer.parseInt(str);
			unsorted[j] = Integer.parseInt(str);
			j++;
		}
		Arrays.sort(sorted);

		if (Arrays.equals(sorted, unsorted)) {
			System.out.println("\t"+search+" are Sorted");
		} else
			System.out.println("\t"+search+" are Not sorted");

	}

	@AfterClass
	public void close() {
		driver.close();
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
			e.printStackTrace();
			System.out.println("error in getTableArray()");
		}
		return (tabArray);
	}

}
