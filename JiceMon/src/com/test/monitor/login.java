package com.test.monitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.test.httpclient.GetToken;
import com.test.httpclient.HttpGet;
import com.test.suite.SendEmail;

public class login {

	@Test
	public void loginTest() throws Exception {
		// int m = 1;
		// int i = 1;
		// int max =100000;
		// while (true) {
		String line = "";
		String httpResults = "";
		String httpResults1 = "";
		GetToken getinfo = new GetToken();
		SendEmail email=new SendEmail();
		// Reporter.log("【测试用例】第"+""+m+""+"次登录！");
		// m=m+1;
		Reporter.log("【测试用例】获取用户名:" + getinfo.getuser() + " " + "成功!");
		String accesstoken = getinfo.gettoken();
		Reporter.log("【测试用例】获取token:" + accesstoken + " " + "成功!");
		Reporter.log("【测试用例】成功登陆后相应请求内容：" + getinfo.getall());
		String URL = getinfo.geturl();
		Reporter.log("【测试用例】获取用户信息请求地址: " + URL);
		HttpURLConnection conn = HttpGet.getConnection(URL);
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		while ((line = reader.readLine()) != null) {
			httpResults = httpResults + line.toString();
		}
		String result = String.valueOf(conn.getResponseCode());
		System.out.println("返回状态码：" + result);
		if (result.compareTo("200") == 0) {
			System.out.println("请求成功!");
			reader.close();
			conn.disconnect();
		} else {
			System.out.println("请求失败!");
			Reporter.log("【测试用例】用户请求返回结果: " + result);
			email.main(null);
			int x = 1;
			while (result.compareTo("200") != 0) {
				Thread.sleep(30000);// 等待30S
				HttpURLConnection conn1 = HttpGet.getConnection(URL);
				BufferedReader reader1 = new BufferedReader(new InputStreamReader(conn1.getInputStream(), "utf-8"));
				while ((line = reader1.readLine()) != null) {
					httpResults = httpResults + line.toString();
					email.main(null);
				}
				
				x++;
				if (x > 2) {
					break;
				}
			}
		}
		Reporter.log("【测试用例】用户请求返回结果: " + httpResults);
		URL getUrl = new URL("https://jice.io/api_v1/apps/796/configs");
		conn = (HttpURLConnection) getUrl.openConnection();
		conn.setRequestProperty("x-auth-token", accesstoken);
		BufferedReader reader1 = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		while ((line = reader1.readLine()) != null) {
			httpResults1 = httpResults1 + line.toString();
		}
		reader.close();
		conn.disconnect();
		System.out.println("打印获取的所有APP:" + httpResults1);
		Reporter.log("【测试用例】登录后获取的app: " + httpResults1);
		Reporter.log("");
		// i++;
		// if (i > max) {
		// break;
		// }
		// try {
		// //Thread.sleep(5000);
		// Thread.sleep(10 * 60 * 1000);// 每次间隔10分钟。
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
	}

}
