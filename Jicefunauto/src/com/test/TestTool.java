package com.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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

import com.sun.jna.platform.win32.WinUser.INPUT;

public class TestTool {

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

	@Parameters("event")
	@Test
	public void Testol(String event) throws Exception{
		driver.findElement(By.xpath(".//div[text()='测试工具']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//div[text()='事件代码']")).click();
		
		
		driver.findElement(By.xpath("//*[@id='jice-main-content']/div/div/div/div[3]/div/div[1]/button")).click();
		Thread.sleep(2000);
		//选择事件
		List<WebElement> debu=driver.findElements(By.className("jice-debug-num"));
		String d1=debu.get(0).findElement(By.tagName("strong")).getText();
		String d2=debu.get(1).findElement(By.tagName("strong")).getText();
		String d3=debu.get(2).findElement(By.tagName("strong")).getText();
		System.out.println("已触发："+d1+","+"未触发："+d2+","+"未定义："+d3);
		
		WebElement input=driver.findElement(By.cssSelector("input[placeholder='搜索事件名称']"));
		input.sendKeys(event);
		input.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		driver.findElement(By.className("jice-debug-left-text")).click();
		
		WebElement chufa=driver.findElement(By.className("jice-debug-top-status"));
		String xian=chufa.getText();
		System.out.println(xian);
        
		//获取事件属性列表
		 System.out.println("--------------------------------------------------------——————————————————");
		 WebElement table=
		 driver.findElement(By.xpath("//*[@id='jice-main-content']/div/div/div/div[3]/div/div[2]/div[2]/div[2]/div[1]/div/div[2]/div[2]/div[1]/div/div/div/div/div/div/table"));
		 List<WebElement> rows = table.findElements(By.tagName("tr"));
		 for(WebElement row:rows){
		 List<WebElement> col = row.findElements(By.tagName("th"));
		 for(WebElement cell:col){
		 System.out.print(cell.getText() + "\t\t\t");
		 }
		 System.out.print("\n");
		 }
		 WebElement table1=
		 driver.findElement(By.xpath("//*[@id='jice-main-content']/div/div/div/div[3]/div/div[2]/div[2]/div[2]/div[1]/div/div[2]/div[2]/div[1]/div/div/div/div/div/div/table/tbody"));
		 List<WebElement> columns = table1.findElements(By.tagName("tr"));
		 for(WebElement column:columns){
		 List<WebElement> col = column.findElements(By.tagName("td"));
		 for(WebElement cell:col){
		 System.out.print(cell.getText() + "\t\t");
		 }
		 System.out.print("\n");
		 }
		 System.out.println("--------------------------------------------------------————————————————");
		
		 driver.findElement(By.xpath(".//span[text()='编辑']/parent::button")).click();
		 //新开数据管理编辑页面
		 Thread.sleep(2000);
		 //窗口切换，获取窗口的句柄
		 String winHandleBefore = driver.getWindowHandle();
		 for (String winHandle : driver.getWindowHandles()) {
			if (winHandle.equals(winHandleBefore)) {
				continue;
			}
			driver.switchTo().window(winHandle);
			break;
		 }
		 WebElement xin=driver.findElement(By.xpath("//*[@id='jice-main-content']/div/div[1]"));
		 String s=xin.getText();
		 System.out.println(s);
		 try{
			 Assert.assertEquals("数据管理 > 编辑",s);
			 System.out.println("成功打开数据管理编辑页面！");
		 }catch(NoSuchElementException e){
			 System.out.println("进入页面失败！！");
		 }
		 
		 //关闭新打开页面
		 
		 driver.close();
//   	 Actions action=new Actions(driver);
//		 action.keyDown(Keys.COMMAND).sendKeys("w").keyUp(Keys.COMMAND).sendKeys(Keys.NULL).perform();
//		 System.out.println("——————————————成功模拟——————————————");
		 
		 //回到原始页面
		 driver.switchTo().window(winHandleBefore);
		 Thread.sleep(3000);
		 //打开分析页面
		 
		 driver.findElement(By.xpath(".//span[text()='分析']/parent::button")).click();
		 Thread.sleep(2000);
		 for (String winHandle : driver.getWindowHandles()) {
				if (winHandle.equals(winHandleBefore)) {
					continue;
				}
				driver.switchTo().window(winHandle);
				break;
			 }
		 String s1=driver.findElement(By.className("jice-title")).getText().substring(0, 4);
		 System.out.println(s1);
		 try{
			 Assert.assertEquals("事件分析",s1);
			 System.out.println("成功打开事件分析页面！");
		 }catch(Exception e){
			 System.out.println("进入页面失败！！");
		 }
		 
		 driver.close();
		 driver.switchTo().window(winHandleBefore);
		 Thread.sleep(3000);
	}

}
