package com.shenghesun.controller.testController;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shenghesun.entity.Mark;

public class MyTest {

	public static void main(String[] args) {
	/*	String resultStr = "{\"output\":{\"result\":[{\"flightNo\":\"KN5987\",\"rate\":\"59.33\",\"depCity\":\"北京南苑\",\"depCode\":\"NAY\",\"arrCity\":\"上海浦东\",\"arrCode\":\"PVG\",\"depPort\":\"北京南苑机场\",\"arrPort\":\"上海浦东国际机场\",\"depTerminal\":\"\",\"arrTerminal\":\"T1\",\"depScheduled\":\"2018-09-30T20:50:00Z\",\"arrScheduled\":\"2018-09-30T23:15:00Z\",\"depEstimated\":\"0001-01-01T00:00:00Z\",\"arrEstimated\":\"0001-01-01T00:00:00Z\",\"depActual\":\"0001-01-01T00:00:00Z\",\"arrActual\":\"0001-01-01T00:00:00Z\",\"codeShares\":[]}]}}";
		JsonParser jp = new JsonParser();  
		JsonObject jo = jp.parse(resultStr).getAsJsonObject();
		JsonArray resultJsonArray = jo.get("output").getAsJsonObject().get("result").getAsJsonArray();
		JsonObject result = jp.parse(resultJsonArray.get(0).toString()).getAsJsonObject();
		String depCity = result.get("depCity").getAsString();
		String arrCity = result.get("arrCity").getAsString();
		System.out.println(depCity);
		System.out.println(arrCity);
		System.out.println(resultJsonArray.toString());*/
		
		String mark = "[{\"mark\":\"110808\"},{\"mark\":\"110808\"},{\"mark\":\"110808\"},{\"mark\":\"\"},{\"mark\":\"\"}]";
		JsonParser jp = new JsonParser();  
		JsonArray jo = jp.parse(mark).getAsJsonArray();
		List<Mark> markList = new ArrayList<>();
		for(int i = 0;i<jo.size();i++) {
			String result = jp.parse(jo.get(i).toString()).getAsJsonObject().get("mark").toString();
			if(result.length()>2) {
				System.out.println(result.length());
				Mark m = new Mark();
				m.setMark(result);
				markList.add(m);
				System.out.println(m);
			}
		}	
	}
}
