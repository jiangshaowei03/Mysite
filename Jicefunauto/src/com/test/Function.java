package com.test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Assert;
import org.openqa.selenium.By;
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

public class Function {

	private WebDriver driver;
	
	@BeforeTest
	public void setUp()throws Exception  {

		System.setProperty("webdriver.chrome.driver", "/driver/chromedriver");

		// 创建一个 ChromeDriver 的接口，用于连接 Chrome
		// 创建一个 Chrome 的浏览器实例
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get("http://jice.stonephp.com");

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
	
//		List<WebElement> content= driver.findElements(By.cssSelector("div>span>div>div"));
//	    System.out.println(content.size());//自动点击下拉菜单中的第一个关键词
//		String ss=content.get(0).getText();
//		System.out.println(ss);
		content.click();
		System.out.println("当前页面的标题2："+ driver.getTitle());
	}
	
	
	@Parameters({"id","name","url","email"})
	@Test
	public void Manage(String aid,String rname,String rurl,String email) throws ClientProtocolException, IOException{
		
		WebElement  menu=driver.findElement(By.className("jice-bottom-menu"));
		menu.click();
		String button1=driver.findElement(By.cssSelector("button[class='tab-title']")).getText();
		System.out.println(button1);
		List<WebElement> al= driver.findElements(By.cssSelector("span.ant-form-text"));
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("count ："+al.size());
		String appid=al.get(1).getText();
		System.out.println("APPID:"+appid);
		
		try {
			Assert.assertEquals(aid, appid);
			System.out.println("成功进入管理页面！");
		} catch (NoSuchElementException e) {
			System.out.println("进入页面失败！");
		}
    //编辑应用
		
		WebElement cre =driver.findElement(By.xpath(".//span[text()='编辑']"));
	    cre.click();
		
		String clname=driver.findElement(By.className("jice-dialog-title")).getText();
		System.out.println("页面："+clname);
		
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys(rname);
		driver.findElement(By.id("homePage")).clear();
		driver.findElement(By.id("homePage")).sendKeys(rurl);
		
//		WebElement icon=driver.findElement(By.className("ant-upload-text"));
//		icon.sendKeys("/Users/jiangshaowei/Desktop/picture");
		
		WebElement save=driver.findElement(By.cssSelector("button[class='ant-btn jice-editapp-save ant-btn-primary ant-btn-lg']"));
		save.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String prompt=driver.findElement(By.className("ant-message")).getText();
		System.out.println("提示："+prompt);
		
    //用户管理-添加授权
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement button2=driver.findElement(By.xpath(".//div[text()='用户管理']"));
		button2.click();
		
		

		
		String author=driver.findElement(By.className("set-up")).getText();
		System.out.println(author);

		WebElement add=driver.findElement(By.xpath(".//span[text()='添加授权']"));
		add.click();
		
		System.out.println(driver.findElement(By.className("jice-dialog-title")).getText());
		
		WebElement inp=driver.findElement(By.xpath(".//input[contains(@id,'---')]"));
		inp.clear();
		inp.sendKeys(email);
		WebElement role=driver.findElement(By.xpath(".//div[contains(@id,'--')]"));
		role.click();
		
		WebElement re=driver.findElement(By.xpath(".//div[text()='管理员']"));
		re.click();	
		List<WebElement> saveButtons = driver.findElements(By.xpath(".//button[@tabindex='0']"));
		int saveSize = saveButtons.size();
		System.out.println("Button count: "+saveSize);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		WebElement saveButton = driver.findElement(By.xpath(".//span[text()='保存']/parent::div/parent::div/parent::button"));
		saveButton.click();
		
		String path="http://jice.stonephp.com/v1/account/exists/"+email;  
        //1.创建客户端访问服务器的httpclient对象   //打开浏览器  
        HttpClient httpclient=new DefaultHttpClient();  
        //2.以请求的连接地址创建get请求对象     //浏览器中输入网址  
        HttpGet httpget=new HttpGet(path);  
        //3.向服务器端发送请求 并且获取响应对象  //浏览器中输入网址点击回车  
        HttpResponse response=httpclient.execute(httpget);  
        //4.获取响应对象中的响应码  
        StatusLine statusLine=response.getStatusLine();//获取请求对象中的响应行对象  
        int responseCode=statusLine.getStatusCode();//从状态行中获取状态码 
		
        if(responseCode==200){
        	System.out.println("已经注册，直接授权！");

        	try {
    			Thread.sleep(2000);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();}
        	System.out.println("页面："+driver.findElement(By.xpath(".//h3[text()='授权成功']")).getText());
        	WebElement invit2=driver.findElement(By.xpath(".//span[text()='关闭']/parent::div/parent::div/parent::button"));
    	    invit2.click();
        	
        }
        else if(responseCode==204){
        	System.out.println("没有注册，需要进行注册！");
        	
        	try {
    			Thread.sleep(2000);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();}
    				
    		System.out.println("页面："+driver.findElement(By.className("jice-dialog-title")).getText());
    		WebElement invit=driver.findElement(By.xpath(".//span[text()='邀请']/parent::div/parent::div/parent::button"));
    		invit.click();

    	    System.out.println("页面："+driver.findElement(By.xpath(".//h3[text()='完成邀请']")).getText());
    	    try {
    			Thread.sleep(2000);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();}
    			
    	    WebElement invit1=driver.findElement(By.xpath(".//span[text()='好的，了解了']/parent::div/parent::div/parent::button"));
    	    invit1.click();
        }
        httpclient.getConnectionManager().shutdown();
		
	}
	
}
