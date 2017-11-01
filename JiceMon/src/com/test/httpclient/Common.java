package com.test.httpclient;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class Common {
	public  static  String getJsonValue(String JsonString,String JsonId){
		String JsonValue="";
		if(JsonString==null||JsonString.trim().length()<1){
			return null;
		}
		try{
			JSONObject obj1=new JSONObject(JsonString);
			JsonValue=(String)obj1.get(JsonId);
		}
		catch(JSONException e){
			e.printStackTrace();
		}
		return JsonValue;
	}

}
