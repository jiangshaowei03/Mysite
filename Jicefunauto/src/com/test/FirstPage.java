package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class FirstPage {

	private WebDriver driver;

	@Test
	public void Page() throws Exception {

		int m = 1;
		int i = 1;
		int max = 300000;
		while (true) {

			System.setProperty("webdriver.chrome.driver", "/driver/chromedriver");

			// 创建一个 ChromeDriver 的接口，用于连接 Chrome
			// 创建一个 Chrome 的浏览器实例
			ChromeOptions chromeOptions = new ChromeOptions();
			//chromeOptions.addArguments("--kiosk");
			driver = new ChromeDriver(chromeOptions);
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.get("https://jice.io");
			System.out.println("当前打开页面的标题是: " + driver.getTitle());
			driver.quit();
			i++;
			if (i > max) {
				break;
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
