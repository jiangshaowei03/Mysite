package com.test.httpclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetApp {

	  public  String getApp() throws Exception{
		    GetToken ul = new GetToken();
		    int len=ul.geturl().length();
		    String autoken=ul.geturl().substring(len-40, 73);
		    System.out.println("getApp token:"+autoken);
		    String line = "";
	        String httpResults1 = ""; 
		    String url="https://jice.io/api_v1/apps/796/configs";
	        HttpURLConnection conn = null;
	        try {
	            // 打开和URL之间的连接
	            URL getUrl = new URL(url);
	            conn = (HttpURLConnection) getUrl.openConnection();
	            // 设置通用的请求属性
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setRequestMethod("GET");//默认是GET方法
	            conn.setUseCaches(false);
	            conn.setInstanceFollowRedirects(true);
	            conn.setRequestProperty("Content-Type", "application/json");
	            conn.setRequestProperty("Charset", "utf-8");
	            conn.setRequestProperty("Accept-Charset", "utf-8");
	            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	            conn.setRequestProperty("x-auth-token", autoken);
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    conn.getInputStream(),"utf-8"));
	            while ((line = reader.readLine()) != null) {
	                httpResults1 = httpResults1 + line.toString();
	            }
	            reader.close();
	            // 断开连接
	            conn.disconnect();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	        return httpResults1;
	    }
}
