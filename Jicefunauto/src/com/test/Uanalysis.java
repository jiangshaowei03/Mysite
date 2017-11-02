package com.test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Uanalysis extends Rand1 {
	private WebDriver driver;

	@BeforeTest
	public void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver", "/driver/chromedriver");

		// 创建一个 ChromeDriver 的接口，用于连接 Chrome
		// 创建一个 Chrome 的浏览器实例
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--kiosk");
		// mac Chrome窗口最大化
		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get("http://jice.stonephp.com");

		System.out.println("当前打开页面的标题是: " + driver.getTitle());

	}

	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
		Thread.sleep(3000);
	}

	@Parameters({ "username", "password" })
	@Test
	public void LoginTest(String uname, String pword) {

		System.out.println(uname);
		System.out.println(pword);

		WebElement element = driver.findElement(By.className("_jice_login"));
		element.click();

		driver.findElement(By.id("email")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pword);
		WebElement login = driver.findElement(By.className("submit-btn"));
		login.click();

		String EmailTest = driver.findElement(By.cssSelector(".user-email")).getText();
		System.out.println("-----------------------------------------------");
		Assert.assertEquals("577641850@qq.com", EmailTest);

	}

	@Parameters("appname")
	@Test
	public void Type(String aname) {
		WebElement button = driver.findElement(By.cssSelector("button[product='jice']"));
		System.out.println(aname);
		button.click();
		System.out.println("当前页面的标题1：" + driver.getTitle());
		// 选择应用

		WebElement down = driver.findElement(By.className("jice-side-app"));
		down.click();
		WebElement input = driver.findElement(By.cssSelector("input[placeholder='搜索']"));
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("jice-app-search-menu")));
		input.sendKeys(aname);
		WebElement content = driver.findElement(By.className("jice-app-search-menu"));

		// List<WebElement> content=
		// driver.findElements(By.cssSelector("div>span>div>div"));
		// System.out.println(content.size());//自动点击下拉菜单中的第一个关键词
		// String ss=content.get(0).getText();
		// System.out.println(ss);
		content.click();
		System.out.println("当前页面的标题2：" + driver.getTitle());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private int random(int min, int max) {
		Random random = new Random();
		return random.nextInt(max) % (max - min + 1) + min;

	}

	@Parameters("gname")
	@Test
	public void UserType(String gname) throws Exception {

		driver.findElement(By.xpath(".//div[text()='用户分析']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//span[text()='创建人群']/parent::div/parent::div/parent::button")).click();
		Thread.sleep(2000);

		String s = driver.findElement(By.className("jice-group-title  ")).getText();
		System.out.println("页面标题：" + s);

		driver.findElement(By.id("groupsname")).sendKeys(gname);

		int M = getRandNum(1, 3);
		System.out.println("当前的随机数值：" + M);
		if (M == 1) {
			// 单独选择一个系统属性
			driver.findElement(By.xpath(".//span[text()='+ 过滤条件']")).click();
			// 一个条件(随机)
			driver.findElement(By.className("ant-select-selection__placeholder")).click();
			List<WebElement> n1 = driver.findElements(By.cssSelector("ul.ant-select-dropdown-menu li"));
			int si = n1.size();
			System.out.println("系统属性维度数量：" + si);
			int i = random(0, si - 1);
			System.out.println("当前选择的维度：" + n1.get(i).getText());
			n1.get(i).click();

			driver.findElement(By
					.xpath("//*[@id='jice-main-content']/div/div/div/div[3]/div/div[3]/div/div[2]/div[1]/div[1]/div/div[3]/div/div/div/div/div"))
					.click();
			Thread.sleep(2000);
			WebElement r = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/ul"));
			List<WebElement> n2 = r.findElements(By.tagName("li"));
			int si1 = n2.size();
			System.out.println("系统属性维度value数量：" + si1);
			int i2 = random(0, si1 - 1);
			System.out.println("当前选择的维度：" + n2.get(i2).getText());
			n2.get(i2).click();
			driver.findElement(By.className("jice-groups-add-groupname")).click();

		} else if (M == 2) {
			// 单独选择一个用户属性
			driver.findElement(By.cssSelector(
					"#jice-main-content > div > div > div > div.jice-group-card > div > div:nth-child(4) > div > div:nth-child(2) > div > div > button > div > span"))
					.click();
			Thread.sleep(2000);
			// 一个条件(随机)
			driver.findElement(By.className("ant-select-selection__placeholder")).click();
			List<WebElement> n3 = driver.findElements(By.cssSelector("ul.ant-select-dropdown-menu li"));
			int si2 = n3.size();
			System.out.println("用户属性维度数量：" + si2);
			int i3 = random(0, si2 - 1);
			System.out.println("当前选择的维度：" + n3.get(0).getText());
			n3.get(0).click();

			driver.findElement(By
					.xpath("//*[@id='jice-main-content']/div/div/div/div[3]/div/div[4]/div/div[2]/div/div[1]/div[1]/div/div[3]/div/div/div/div/div"))
					.click();
			Thread.sleep(5000);
			WebElement r1 = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/ul"));
			List<WebElement> n4 = r1.findElements(By.tagName("li"));
			int si3 = n4.size();
			System.out.println("用户属性维度value数量：" + si3);
			int i4 = random(0, si3 - 1);
			System.out.println("当前选择的维度：" + n4.get(i4).getText());
			n4.get(i4).click();
			driver.findElement(By.className("jice-groups-add-groupname")).click();

		} else {
			// 单独选择一个互动事件
			driver.findElement(By.cssSelector(
					"#jice-main-content > div > div > div > div.jice-group-card > div > div.col-22.group-add-row.jice-noborder.jice-groups-selectrow > div > div.groups-event-width.group-filter-item.group-event-hidden > div > button > div > span"))
					.click();
			Thread.sleep(2000);
			// 一个条件(随机)
			// driver.findElement(By.className("ant-select-selection__placeholder")).click();
			// List<WebElement>
			// n5=driver.findElements(By.cssSelector("ul.ant-select-dropdown-menu
			// li"));
			// int si4=n5.size();
			// System.out.println("用户属性维度数量："+si4);
			// int i5=random(0,si4-1);
			// System.out.println("当前选择的维度："+n5.get(i5).getText());
			// n5.get(i5).click();

			driver.findElement(By
					.xpath("//*[@id='jice-main-content']/div/div/div/div[3]/div/div[5]/div/div[2]/div/div/div[3]/div/div/div/div/div"))
					.click();
			Thread.sleep(2000);
			WebElement r2 = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/ul"));
			List<WebElement> n6 = r2.findElements(By.tagName("li"));
			int si5 = n6.size();
			System.out.println("互动事件value数量：" + si5);
			int i6 = random(0, si5 - 1);
			System.out.println("当前选择的互动事件是：" + n6.get(i6).getText());
			n6.get(i6).click();
			driver.findElement(By.className("jice-groups-add-groupname")).click();
		}

		driver.findElement(
				By.xpath("//*[@id='jice-main-content']/div/div/div/div[3]/div/div[6]/span[1]/div/button/div/div/span"))
				.click();
		Thread.sleep(2000);
		String n = driver.findElement(By.className("ant-message")).getText();
		try {
			Assert.assertEquals(n, "保存成功");
			System.out.println(n);
		} catch (Exception e) {
			System.out.println("保存失败");
		}
	}

	@Parameters("event1")
	@Test
	public void Legend(String event1) throws Exception {

		// 获取用户分析主页面的图形数据
		// 概览
		driver.findElement(By.xpath(".//div[text()='用户分析']")).click();
		Thread.sleep(4000);
		String gailan = driver.findElement(By.className("group-overview")).getText();
		System.out.println("概览：" + "\n" + gailan);

		// 地域分布
		int y = 142;
		WebElement diyu = driver.findElement(By.xpath(
				"//*[@id='jice-main-content']/div/div/div/div[3]/div[3]/div[1]/div[2]/div/div[2]/div[2]/div[1]"));
		// 定义一个初始坐标然后移动鼠标增加横坐标的像素；纵坐标像素不变，取值
		for (int x = 66; x <= 283; x = x + 70) {
			new Actions(driver).moveToElement(diyu, x, y).build().perform();
			String diyu1 = driver
					.findElement(By
							.xpath("//*[@id='jice-main-content']/div/div/div/div[3]/div[3]/div[1]/div[2]/div/div[2]/div[2]/div[2]"))
					.getText();
			System.out.println("---------地域分布--------");
			System.out.println("移动鼠标：省份：" + diyu1);
		}
		Thread.sleep(1000);

		// 操作系统
		WebElement caozuo = driver.findElement(By.xpath(
				"//*[@id='jice-main-content']/div/div/div/div[3]/div[3]/div[1]/div[3]/div/div[2]/div[2]/div[1]"));
		// 固定点像素取值
		new Actions(driver).moveToElement(caozuo, 191, 138).build().perform();
		Thread.sleep(1000);
		String caozuo1 = driver
				.findElement(By
						.xpath("//*[@id='jice-main-content']/div/div/div/div[3]/div[3]/div[1]/div[3]/div/div[2]/div[2]/div[2]"))
				.getText();
		System.out.println("---------操作系统--------");
		System.out.println("移动鼠标：操作系统" + caozuo1);
		Thread.sleep(1000);

		// 浏览器
		int x = 100;
		WebElement bro = driver.findElement(By.xpath(
				"//*[@id='jice-main-content']/div/div/div/div[3]/div[3]/div[2]/div[1]/div/div[2]/div[2]/div[1]"));
		// 定义一个初始纵坐标然后移动鼠标增加横坐标的像素；横坐标像素不变，取值
		for (int m = 57; m <= 150; m = m + 46) {
			new Actions(driver).moveToElement(bro, x, m).build().perform();
			String liu = driver
					.findElement(By
							.xpath("//*[@id='jice-main-content']/div/div/div/div[3]/div[3]/div[2]/div[1]/div/div[2]/div[2]/div[2]"))
					.getText();
			System.out.println("---------浏览器--------");
			System.out.println("移动鼠标：浏览器：" + liu);
		}
		Thread.sleep(2000);

		// 设备分辨率i
		int l = 67;
		WebElement res = driver.findElement(By.xpath(
				"//*[@id='jice-main-content']/div/div/div/div[3]/div[3]/div[2]/div[2]/div/div[2]/div[2]/div[1]"));
		// 定义一个初始纵坐标然后移动鼠标增加横坐标的像素；横坐标像素不变，取值
		for (int m = 45; m <= 163; m = m + 23) {
			new Actions(driver).moveToElement(res, l, m).build().perform();
			String fen = driver
					.findElement(By
							.xpath("//*[@id='jice-main-content']/div/div/div/div[3]/div[3]/div[2]/div[2]/div/div[2]/div[2]/div[2]"))
					.getText();
			System.out.println("---------设备分辨率--------");
			System.out.println("移动鼠标：设备分辨率：" + fen);
		}
		Thread.sleep(1000);

		// 来源类型
		int z = 68;
		WebElement source = driver.findElement(By.xpath(
				"//*[@id='jice-main-content']/div/div/div/div[3]/div[3]/div[2]/div[3]/div/div[2]/div[2]/div[1]"));
		// 定义一个初始纵坐标然后移动鼠标增加横坐标的像素；横坐标像素不变，取值
		for (int m = 63; m <= 145; m = m + 58) {
			new Actions(driver).moveToElement(source, z, m).build().perform();
			String lai = driver
					.findElement(By
							.xpath("//*[@id='jice-main-content']/div/div/div/div[3]/div[3]/div[2]/div[3]/div/div[2]/div[2]/div[2]"))
					.getText();
			System.out.println("---------来源类型--------");
			System.out.println("移动鼠标：来源类型：" + lai);
		}
		Thread.sleep(1000);

		// 自定义列
		System.out.println("---------数据列表--------");
		driver.findElement(By.xpath(".//span[text()='自定义列']")).click();
		Thread.sleep(1000);
		WebElement zi = driver.findElement(By.className("ant-dropdown-menu"));
		List<WebElement> li = zi.findElements(By.tagName("li"));
		System.out.println("自定义列的数量：" + li.size());
		// 随机选择列排除原来已存在的
		int X = getRandNum(0, 14);
		if (X == 2 || X == 5 || X == 6) {
			int Z = getRandNum(X + 1, 14);
			if (Z == 5 || Z == 6) {
				int N = getRandNum(Z + 1, 14);
				if (N == 6) {
					int O = getRandNum(N + 1, 14);
					System.out.println("当前的随机数值：" + O);
					li.get(O).click();
				}
			} else {
				System.out.println("当前的随机数值：" + Z);
				li.get(Z).click();
			}
		} else {
			System.out.println("当前的随机数值：" + X);
			li.get(X).click();
			Thread.sleep(1000);
		}
		driver.findElement(By
				.xpath("//*[@id='jice-main-content']/div/div/div/div[3]/div[4]/div/div/div/div[2]/div[1]/div/div[1]/button/div/div"))
				.click();
		
		driver.findElement(By.xpath("//*[@id='jice-main-content']/div/div/div/div[3]/div[4]/div/div/div/div[2]/div[2]/div/div[1]/div[1]/div[2]/div[1]/button")).click();
		Thread.sleep(2000);
		WebElement dri=driver.findElement(By.xpath(".//body/div[last()]"));
		List<WebElement> dri1=dri.findElements(By.xpath(".//div/div/div/div/span/div/div/div"));
		System.out.println(dri1.size());
		String h=dri1.get(1).getText();
		System.out.println("当前选择的维度："+h);
		dri1.get(1).click();
		Thread.sleep(2000);
		
		WebElement sel = driver.findElement(By.xpath("//*[@id='jice-main-content']/div/div/div/div[3]/div[4]/div/div/div/div[2]/div[2]/div/div[1]/div[2]/input"));
		sel.click();
		//默认下拉选择的是操作系统校验内容填写选择浏览器的内容
		sel.sendKeys(event1);
		Thread.sleep(2000);
		
		System.out.println("--------------------------------------------------------——————————————————");
		 WebElement table=
		 driver.findElement(By.xpath("//*[@id='jice-main-content']/div/div/div/div[3]/div[4]/div/div/div/div[3]/div/div[1]/div/div[1]/table"));
		 List<WebElement> rows = table.findElements(By.tagName("tr"));
		 for(WebElement row:rows){
		 List<WebElement> col = row.findElements(By.tagName("th"));
		 for(WebElement cell:col){
		 System.out.print(cell.getText() + "\t\t");
		 }
		 System.out.print("\n");
		 }
		 WebElement table1=
		 driver.findElement(By.xpath("//*[@id='jice-main-content']/div/div/div/div[3]/div[4]/div/div/div/div[3]/div/div[1]/div/div[2]/table"));
		 List<WebElement> columns = table1.findElements(By.tagName("tr"));
		 for(WebElement column:columns){
		 List<WebElement> col = column.findElements(By.tagName("td"));
		 for(WebElement cell:col){
		 System.out.print(cell.getText() + "\t");
		 }
		 System.out.print("\n");
		 }
		 System.out.println("--------------------------------------------------------————————————————");
	}

}
