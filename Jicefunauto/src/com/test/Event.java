package com.test;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Event extends Rand {
	private WebDriver driver;

	@BeforeTest
	public void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver", "/driver/chromedriver");

		// 创建一个 ChromeDriver 的接口，用于连接 Chrome
		// 创建一个 Chrome 的浏览器实例
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--kiosk");
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

	@Test
	public void Eventanalysis() throws Exception {
		// 默认进入到事件概览模块
		// 默认时间周期（7天）

		int y = 162;
		Random r = new Random();
		driver.findElement(By.xpath(".//span[text()='事件概览']")).click();

		// 选择纵坐标显示类型
		driver.findElement(By.className("ant-select-selection__rendered")).click();
		WebElement yl = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/ul"));
	    List<WebElement> sl=yl.findElements(By.tagName("li"));
	    System.out.println("纵坐标类型数量：" +sl.size());
		int R3[] = randomCommon(1, 3, 1);
		System.out.println("获取的纵坐标类型是："+sl.get(R3[0]).getText());
		sl.get(R3[0]).click();
		driver.findElement(By.className("jice-title")).click();
        WebElement canvas = driver.findElement(By.xpath(".//canvas[1]"));
		
		// Actions drawPen = new Actions(driver);
		// drawPen.clickAndHold(canvas).moveByOffset(431, 617).moveByOffset(431,
		// 617).moveByOffset(-431, -617)
		// .moveByOffset(-431, -617).release().perform();
        

		// 取6天的数据
		 System.out.println("----默认最近7天内的数据----");
		 for (int x = 58; x <= 1037; x = x + 188) {
		 new Actions(driver).moveToElement(canvas, x, y).build().perform();
		 String canvas1 =
		 driver.findElement(By.className("jice-event-overview-line-box")).getText();
		 System.out.println("移动鼠标：日期(天）：" + canvas1);
		 }
		 Thread.sleep(2000);
		
         // 时间周期选择-最近30天 
		 WebElement ins=driver.findElement(By.className("jice-datepicker"));
		 ins.click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath(".//a[text()='最近30天']")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.cssSelector(".ant-calendar-ok-btn")).click();
		 Thread.sleep(2000);
		
		 System.out.println("----最近30天内的数据----");
		 WebElement canvas4 = driver.findElement(By.xpath(".//canvas[1]"));
		 for(int x=58;x<=1037;x=x+188){
		 new Actions(driver).moveToElement(canvas4, x, y).build().perform();
		 String
		 canvas1=driver.findElement(By.className("jice-event-overview-line-box")).getText();
		 System.out.println("移动鼠标：日期："+canvas1);
		 }

		 // 天、小时切换
		 WebElement ins1=driver.findElement(By.className("jice-datepicker"));
		 ins1.click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath(".//a[text()='最近7天']")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.cssSelector(".ant-calendar-ok-btn")).click();
		 Thread.sleep(2000);
		 
		 driver.findElement(By.xpath(".//span[text()='小时']")).click();
		 Thread.sleep(2000);
		 WebElement canvas2 = driver.findElement(By.xpath(".//canvas[1]"));
		 for (int x = 58; x <= 1037; x = x + 188) {
		 new Actions(driver).moveToElement(canvas2, x, y).build().perform();
		 String canvas1 =
		 driver.findElement(By.className("jice-event-overview-line-box")).getText();
		 System.out.println("移动鼠标：日期（小时）：" + canvas1);
		 }
		 	
		
		// 搜索事件名称-并定位事件
		 WebElement l = driver.findElement(By.cssSelector("input.ant-input"));
		 l.click();
		
		 List<WebElement> list =
		 driver.findElements(By.cssSelector("label.ant-checkbox-wrapper"));
		 System.out.println("事件总数：" + list.size());
		 int s[] = randomCommon(2, 4, 1);
		 System.out.println("选中事件数量：" + s[0]);
		 if (s[0] == 1) {
		 String num1 = list.get(r.nextInt(7)).getText();
		 list.get(r.nextInt(7)).click();
		 Thread.sleep(2000);
		 System.out.println("当前选中的事件：" + num1);
		
		 } else if (s[0] == 2) {
		 int R[] = randomCommon(1, 7, 2);
		 String num1 = list.get(R[0]).getText();
		 String num2 = list.get(R[1]).getText();
		 list.get(R[0]).click();
		 list.get(R[1]).click();
		 Thread.sleep(2000);
		 System.out.println("当前选中的事件：" + num1 + "," + num2);
		 } else {
		 int R1[] = randomCommon(1, 7, 3);
		 String num1 = list.get(R1[0]).getText();
		 String num2 = list.get(R1[1]).getText();
		 String num3 = list.get(R1[2]).getText();
		 list.get(R1[0]).click();
		 list.get(R1[1]).click();
		 list.get(R1[2]).click();
		 Thread.sleep(2000);
		 System.out.printf("当前选中的事件：" + num1 + "," + num2 + "," + num3);
		 }
//		  Actions actions = new Actions(driver);
//		  actions.moveByOffset(720, 786).click().build().perform();
		 
		 
		 driver.findElement(By.xpath("/html/body")).click();
		 Thread.sleep(2000);
		 
		 driver.findElement(By.xpath(".//span[text()='天']")).click();
		 Thread.sleep(2000);
		 WebElement canvas3 = driver.findElement(By.xpath(".//canvas[1]"));
		 for (int x = 58; x <= 1037; x = x + 188) {
		 new Actions(driver).moveToElement(canvas3, x, y).build().perform();
		 String canvas1 =
		 driver.findElement(By.className("jice-event-overview-line-box")).getText();
		 System.out.println("移动鼠标-多事件：日期（天）：" + canvas1);
		
		 }


		 // 获取维度
		 driver.findElement(By.xpath(".//div[text()='请选择维度']")).click();
		 Thread.sleep(2000);
		 WebElement wei =
		 driver.findElement(By.className("ant-select-dropdown-menu-item-group-list"));
		 List<WebElement> du = wei.findElements(By.tagName("li"));
		
		 System.out.println("系统维度数量：" + du.size() );
		 du.get(2).getText();
		 int R2[] = randomCommon(1, 15, 1);
		 System.out.println("获取的系统维度是："+du.get(R2[0]).getText());
		 du.get(R2[0]).click();
		 driver.findElement(By.xpath("/html/body")).click();
		 
		// 拖动页面到顶部或底部   
//	     JavascriptExecutor je = (JavascriptExecutor) driver;             
//	     //执行js语句，拖拽浏览器滚动条，直到该元素到顶部，马上就不可以见  
//	     je.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.className("jice-datepicker"))); 

		 Thread.sleep(2000);
		 //获取整个table数据	
		 System.out.println("--------------------------------------------------------——————————————————");
		 WebElement table=
		 driver.findElement(By.xpath("//*[@id='jice-main-content']/div/div/div/div[2]/div[3]/div[2]/div[2]/div/div[1]/table"));
		 List<WebElement> rows = table.findElements(By.tagName("tr"));
		 for(WebElement row:rows){
		 List<WebElement> col = row.findElements(By.tagName("th"));
		 for(WebElement cell:col){
		 System.out.print(cell.getText() + "\t\t");
		 }
		 System.out.print("\n");
		 }
		 WebElement table1=
		 driver.findElement(By.xpath("//*[@id='jice-main-content']/div/div/div/div[2]/div[3]/div[2]/div[2]/div/div[2]/table"));
		 List<WebElement> columns = table1.findElements(By.tagName("tr"));
		 for(WebElement column:columns){
		 List<WebElement> col = column.findElements(By.tagName("td"));
		 for(WebElement cell:col){
		 System.out.print(cell.getText() + "\t\t");
		 }
		 System.out.print("\n");
		 }
		 System.out.println("--------------------------------------------------------————————————————");
		
		//导出
		//driver.findElement(By.xpath("//*[@id='jice-main-content']/div/div/div/div[2]/div[3]/div[2]/div[1]/div[2]/div/button")).click();
		WebElement dao=driver.findElement(By.xpath(".//span[text()='导 出']/parent::div/parent::div/parent::button"));
		dao.click();
		
	}

}
