package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Firefoxtest {
	public static void main(String[] args) {
		// 如果你的 FireFox 没有安装在默认目录，那么必须在程序中设置
		System.setProperty("webdriver.gecko.driver", "/driver/geckodriver");
		// 创建一个 FireFox 的浏览器实例
		
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		
		cap.setCapability("marionette", true);
		
		WebDriver driver = new FirefoxDriver(cap);

		
		driver.manage().window().maximize();
		
		// 让浏览器访问 Baidu
		driver.get("http://jice.stonephp.com");
		// 用下面代码也可以实现
		// driver.navigate().to("http://jice.stonephp.com");

		// 获取 网页的 title
		System.out.println("第一个页面的标题: " + driver.getTitle());

		// 通过 id 找到 input 的 DOM
		WebElement element = driver.findElement(By.id("kw"));

		// 输入关键字
		element.sendKeys("testng");
  
		System.out.println("-------------------------------");
		// 提交 input 所在的 form
		element.submit();

		// 通过判断 title 内容等待搜索页面加载完毕，Timeout 设置10秒
		// (new WebDriverWait(driver, 120)).until(new
		// ExpectedCondition<Boolean>() {
		// public Boolean apply(WebDriver d) {
		// return d.getTitle().toLowerCase().contains("testng");
		// }
		// });

		// 显示搜索结果页面的 title
		System.out.println("第二个页面的标题: " + driver.getTitle());

		// 关闭浏览器
		driver.quit();
	}
}
