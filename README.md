# Selenium
for (int i = 2; i < n + 2; i++) {
			driver.findElement(
					By.xpath("//*[@id='select_" + i + "_chosen']/a/span"))
					.click();
			driver.findElement(
					By.xpath("//*[@id='select_" + i + "_chosen']/div/div/input"))
					.sendKeys(nominees[i - 2] + Keys.ENTER);
			driver.findElement(By.xpath("//*[@id='input_" + i + "']"))
					.sendKeys(amounts[i - 2]);
			Thread.sleep(450);

		}

https://seleniumhq.github.io/selenium/docs/api/java/index.html?org/openqa/selenium/Alert.html
