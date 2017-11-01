package com.test.httpclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


public class GetUser {
	    public String getHttpRespone() throws Exception {
	        String line = "";
	        String httpResults = "";  
	        try {
	        	GetToken ul=new GetToken();
	            HttpURLConnection conn = HttpGet.getConnection(ul.geturl());
	            System.out.println(ul.geturl());
	            //DataOutputStream out = null;
	            // 建立实际的连接
	            //connection.connect();
	            //创建一个新的数据输出流，将数据写入指定基础输出流
	            //out = new DataOutputStream(connection.getOutputStream());
	            //out.flush();//清空缓冲区的数据流
	            //out.close();//
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    conn.getInputStream(),"utf-8"));
	            while ((line = reader.readLine()) != null) {
	                httpResults = httpResults + line.toString();
	            }
	            reader.close();
	            // 断开连接
	            conn.disconnect();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return httpResults;
	    }

}
