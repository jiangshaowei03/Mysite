package com.test.httpclient;

import net.sf.json.JSONObject;

public class GetToken {
	String username="695141191@qq.com";
	String password="admaster123";
	String httpResult,accessToken,postResult;
	String Info = "access_token" + "=" + "" + "&" + "username" + "=" + "695141191@qq.com" + "&" + "password"
			+ "=" + "admaster123";
	HttpPost post = new HttpPost();
	public String getuser(){
	        return username;
	    }
	public String getpaword(){
		return password;
	}
	public String getall() throws Exception{
		return postResult;
	}
	public String geturl() throws Exception{
		System.out.println("获取的token：" + accessToken);
		String Url = ("https://jice.io/v1/oauth/current/" + accessToken);
		System.out.println("URL:" + Url);
		return Url;
	}
	
	public String gettoken() throws Exception{

		postResult = post.GetResponse(Info);
		System.out.println("请求相应内容："+postResult);
		String str1=postResult.replaceAll(" ", "").substring(1, postResult.length()-1);
		System.out.println("转化后的内容："+str1);
		String str = str1.replace("\\", "");
		System.out.println("转义后的内容："+str);
		JSONObject jsonObject = JSONObject.fromObject(str);
		accessToken = jsonObject.getString("access_token");
		return accessToken;
	}

	
}
