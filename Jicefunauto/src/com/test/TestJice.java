package com.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestJice extends Rand {

	private WebDriver driver;
    
	
	@BeforeTest
	public void setUp()throws Exception {

		System.setProperty("webdriver.chrome.driver", "/driver/chromedriver");

		// 创建一个 ChromeDriver 的接口，用于连接 Chrome
		// 创建一个 Chrome 的浏览器实例
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get("http://jice.io");

		System.out.println("当前打开页面的标题是: " + driver.getTitle());

	}
	
	@AfterTest
	public void tearDown() throws Exception{
		driver.quit();
		Thread.sleep(3000);
	}

	@Parameters({"username","password"})
	@Test
	public void LoginTest(String uname,String pword) {
		
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
	
		@Test
		public void logout(){
			
		WebElement logout = driver.findElement(By.id("logout-btn"));
		logout.click();

		try {
			driver.getCurrentUrl().equals("http://jice.stonephp.com/login");
			System.out.println("退出成功！");
		} catch (NoSuchElementException e) {
			System.out.println("退出失败！");
		}
	}

	@Test
	public void RegistTest() {
		WebElement regist = driver.findElement(By.linkText("申请试用"));
		regist.click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement apply = driver.findElement(By.partialLinkText("首页"));
		System.out.println(apply);
		Assert.assertEquals("https://jice.io/", apply.getAttribute("href"));

		apply.click();

		try {
			driver.getCurrentUrl().equals("https://jice.io/");
			System.out.println("成功进入申请试用并返回首页！");
		} catch (NoSuchElementException e) {
			System.out.println("进入页面失败！");
		}

	}

	@Test
	public void SelectProduce() {
		
		WebElement select = driver.findElement(By.className("dropdown-toggle"));
		select.click();
		
		List<WebElement> list = driver.findElements(By.cssSelector("ul.dropdown-menu li"));
		System.out.println("菜单数量为:"+list.size());
		Assert.assertEquals(3, list.size());

		String point1 = list.get(0).findElement(By.tagName("a")).getText();
		String point2 = list.get(1).findElement(By.tagName("a")).getText();
		String point3 = list.get(2).findElement(By.tagName("a")).getText();

		Assert.assertEquals("归因分析", point1);
		Assert.assertEquals("用户运营", point2);
		Assert.assertEquals("微信小程序", point3);

		int M[]=randomCommon(1,3,1);
		System.out.println("随机数字为:"+M);
		if(M[0]==1){
		
		WebElement click1 = list.get(0).findElement(By.tagName("a"));
		click1.click();
		System.out.println("当前打开页面的标题是: " + driver.getTitle());
		WebElement demo = driver.findElement(By.id("view-conversion-demo"));
		demo.click();
		try {
			driver.getCurrentUrl().equals("https://conv.jice.io/apps");
			System.out.println("成功进入归因分析demo！");
		} catch (NoSuchElementException e) {
			System.out.println("进入页面失败！");
		}
		}else if(M[1]==2){

		System.out.println("当前打开页面的标题是: " + driver.getTitle());
		WebElement click2 = list.get(1).findElement(By.tagName("a"));
		click2.click();
		System.out.println("当前打开页面的标题是: " + driver.getTitle());
//		WebElement demo1 = driver.findElement(By.className("button btn-blue _jice_login"));
//		demo1.click();
		}else{
		WebElement click3 = list.get(2).findElement(By.tagName("a"));
		click3.click();
		System.out.println("当前打开页面的标题是: " + driver.getTitle());
		}
	}
	
	@Parameters("appname")
	@Test
	public void Type(String aname){
		WebElement button=driver.findElement(By.cssSelector("button[product='jice']"));
		System.out.println(aname);
		button.click();
		System.out.println("当前页面的标题1："+ driver.getTitle());
		//选择应用
		
		WebElement down=driver.findElement(By.className("jice-side-app"));
		
		System.out.println(down);
		down.click();
		WebElement input=driver.findElement(By.cssSelector("input[placeholder='搜索']"));
		WebDriverWait wait=new WebDriverWait(driver,2);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("jice-app-search-menu")));
		input.sendKeys(aname);
		WebElement content=driver.findElement(By.className("jice-app-search-menu"));
	
		content.click();//自动点击下拉菜单中的第一个关键词
		
		
		System.out.println("当前页面的标题2："+ driver.getTitle());
	}

}
