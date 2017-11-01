package com.test.httpclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPost {
	public String GetResponse(String Info) throws Exception {

		String line="";
		String Results="";
		String path = "https://jice.io/v1/oauth/login";
		// 打开和URL之间的连接
		try {
			URL postUrl = new URL(path);
			HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
			// 设置通用的请求属性
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("accept", "*/*");
			// connection.setRequestProperty("username", "695141191@qq.com");
			// connection.setRequestProperty("password", "admaster123");
			// connection.setRequestProperty("connection", "Keep-Alive");
			// 获取向服务器写出数据的流
			OutputStream os = connection.getOutputStream();
			os.write(Info.getBytes());
			os.flush();
			// 得到服务器写回的响应数据
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			while ((line = reader.readLine()) != null) {
				Results = Results + line.toString();
			}
			reader.close();
			// 断开连接
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Results;

	}
}
