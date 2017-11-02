package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GoogleTest {

	public static void main(String[] args) {
		String url = "http://www.baidu.com";
		/*
		 * String url =
		 * "http://mobile.service.com/user/CheckLogin.aspx?UserName=aaa@aa.aa&Password=123456&key=889";
		 * System.setProperty(
		 * "webdriver.firefox.bin","D:\\Program Files\\Mozilla Firefox\\firefox.exe"
		 * ); WebDriver driver = new FirefoxDriver(); WebDriver ie_driver = new
		 * InternetExplorerDriver(); //打开IE
		 */
		// 打开chrome

		WebDriver driver = new ChromeDriver();

		driver.get(url);
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("hello selenium!");
		element.submit();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("页面Title:" + driver.getTitle() + "\n 页面URL:" + driver.getCurrentUrl());
		/*
		 * System.out.println("返回当前的浏览器的窗口句柄:"+driver.getWindowHandle());   
		 * String s=driver.getPageSource();s=s.substring(s.indexOf("{"), s.
		 * indexOf("}"));   System.out.println("当前页面的源码:"+s);  
		 */
		driver.quit();
	}

}
