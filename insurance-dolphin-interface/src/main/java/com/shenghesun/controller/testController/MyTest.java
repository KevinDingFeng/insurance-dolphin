package com.shenghesun.controller.testController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MyTest {

	public static void main(String[] args) {
	//	String resultStr = "{\"status\": 0,\"message\": \"query ok\","+"\"result\": {\"address\": \"北京市海淀区镜桥\","+				"\"address_component\": {\"nation\": \"中国\",\"province\": \"北京市\","+				"\"city\": \"北京市\",\"district\": \"海淀区\","+			"\"street\": \"镜桥\",\"street_number\": \"镜桥\"}}}";				
		String resultStr = "{\"output\":{\"result\":[{\"flightNo\":\"KN5987\",\"rate\":\"59.33\",\"depCity\":\"北京南苑\",\"depCode\":\"NAY\",\"arrCity\":\"上海浦东\",\"arrCode\":\"PVG\",\"depPort\":\"北京南苑机场\",\"arrPort\":\"上海浦东国际机场\",\"depTerminal\":\"\",\"arrTerminal\":\"T1\",\"depScheduled\":\"2018-09-30T20:50:00Z\",\"arrScheduled\":\"2018-09-30T23:15:00Z\",\"depEstimated\":\"0001-01-01T00:00:00Z\",\"arrEstimated\":\"0001-01-01T00:00:00Z\",\"depActual\":\"0001-01-01T00:00:00Z\",\"arrActual\":\"0001-01-01T00:00:00Z\",\"codeShares\":[]}]}}";
		/*JsonParser jp = new JsonParser();		//将json字符串转化成json对象           
		JsonObject jo = jp.parse(resultStr).getAsJsonObject();  //获取message对应的值           
		String message = jo.get("message").getAsString();   
		System.out.println("message：" + message);   //获取address对应的值            
		String address = jo.get("result").getAsJsonObject().get("address").getAsString();    
		System.out.println("address：" + address);    //获取city对应的值            
		String nation = jo.get("result").getAsJsonObject().get("address_component")					
		.getAsJsonObject().get("nation").getAsString();
		System.out.println("nation：" + nation);*/
		JsonParser jp = new JsonParser();
		JsonObject jo = jp.parse(resultStr).getAsJsonObject();
		JsonArray resultJsonArray = jo.get("output").getAsJsonObject().get("result").getAsJsonArray();
		JsonObject result = jp.parse(resultJsonArray.get(0).toString()).getAsJsonObject();
		String depCity = result.get("depCity").getAsString();
		String arrCity = result.get("arrCity").getAsString();
		System.out.println(depCity);
		System.out.println(arrCity);
		System.out.println(resultJsonArray.toString());
		//String flightNo = jo.get("flightNo").getAsString();
		//System.out.println(flightNo);
		

		
	}
}
