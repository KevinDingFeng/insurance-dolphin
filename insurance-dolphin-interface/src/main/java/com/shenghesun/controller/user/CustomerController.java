package com.shenghesun.controller.user;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shenghesun.common.BaseResponse;
import com.shenghesun.util.HttpUtils;


@RestController
@RequestMapping("/customer")
class CustomerController {


	/**通过航班号获取起始地信息
	 * * @Title: login
	 * @Description: TODO
	 * @author dan
	 * @date 上午11:48:54
	 * @param telNumber
	 * @return
	 */
	@GetMapping("flight11")
	@ResponseBody
	public Object flight(String flightNo,String flightDate,HttpServletRequest request){
		BaseResponse baseResponse = new BaseResponse();

		String host = "https://apemesh.market.alicloudapi.com";
		String path = "/devices/055a2472-fd32-595f-add1-726f9987d4d3/invoke-action";
		String method = "POST";
		String appcode = "bb00dde15da14747be5985347ab2986a";
		Map<String, String> headers = new HashMap<String, String>();
		//最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		//根据API的要求，定义相对应的Content-Type
		headers.put("Content-Type", "application/json; charset=UTF-8");
		Map<String, String> querys = new HashMap<String, String>();
		//String bodys = "API请求必须使用POST方法请求头部必须包含\"Content-Type:application/json\"信息在API请求的body中填入以下JSON信息，其中serviceID,actionName字段的内容固定为“urn:cdif-io:serviceID:航班信息查询服务”和“起降时间查询”，input字段的内容填入要查询的航班号（flightNo）和查询日期（date）。范例如下：{\"serviceID\":\"urn:cdif-io:serviceID:航班信息查询服务\",\"actionName\":\"起降时间查询\",\"input\":{\"flightNo\":\"SC4602\",\"date\":\"2017-05-20\"}}以上flightNo字段为字符串类型，其内容为航空公司的IATA代码（暂时不支持三位ICAO代码）加上航班编号，例如MU5077,SC4602等，字符串中不能包含空格，中文或特殊字符。date字段为字符串类型，格式为YYYY-MM-DD。API调用请求的完整curl范例如下：curl-H'Authorization:APPCODE你自己的AppCode'-H'Content-Type:application/json'-XPOST-d'{\"serviceID\":\"urn:cdif-io:serviceID:航班信息查询服务\",\"actionName\":\"起降时间查询\",\"input\":{\"flightNo\":\"SC4602\",\"date\":\"2017-05-20\"}}'http://apemesh.market.alicloudapi.com/devices/055a2472-fd32-595f-add1-726f9987d4d3/invoke-action";
		//String bodys = "{\"serviceID\":\"urn:cdif-io:serviceID:航班信息查询服务\",\"actionName\":\"起降时间查询\",\"input\":{\"flightNo\":\"KN5987\",\"date\":\"2018-09-30\"}}";
		String bodys = "{\"serviceID\":\"urn:cdif-io:serviceID:航班信息查询服务\",\"actionName\":\"起降时间查询\",\"input\":{\"flightNo\":\""+flightNo+"\",\"date\":\""+flightDate+"\"}}";

		//完整请求示例     curl-H'Authorization:APPCODE你自己的AppCode'-H'Content-Type:application/json'-XPOST-d'{\"serviceID\":\"urn:cdif-io:serviceID:航班信息查询服务\",\"actionName\":\"起降时间查询\",\"input\":{\"flightNo\":\"SC4602\",\"date\":\"2017-05-20\"}}'http://apemesh.market.alicloudapi.com/devices/055a2472-fd32-595f-add1-726f9987d4d3/invoke-action
		HttpResponse response = null;
		String resultStr = null;
		Map<String, String> map = new HashMap<>();
		try {

			response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			//HttpResponse response = HttpUtils.doPost("http://www.baidu.com", "", "POST", headers, querys, bodys);
			int status = response.getStatusLine().getStatusCode();
			if ((status >= 200) && (status < 300)) {
				resultStr = EntityUtils.toString(response.getEntity());
				System.out.println(resultStr);
				JsonParser jp = new JsonParser();
				JsonObject jo = jp.parse(resultStr).getAsJsonObject();
				JsonArray resultJsonArray = jo.get("output").getAsJsonObject().get("result").getAsJsonArray();
				JsonObject result = jp.parse(resultJsonArray.get(0).toString()).getAsJsonObject();
				String depCity = result.get("depCity").getAsString();
				String arrCity = result.get("arrCity").getAsString();
				map.put("depCity", depCity);
				map.put("arrCity", arrCity);
				//System.out.println(EntityUtils.toString(response.getEntity()));
			}else {
				baseResponse.setSuccess(false);
	            return baseResponse;
			}
			
		} catch (Exception e) {
			baseResponse.setSuccess(false);
            return baseResponse;
		}
		baseResponse.setData(map);
		return baseResponse;

	}
	
	@GetMapping("flight")
	@ResponseBody
	public Object flightNo(String flightNo,String flightDate,HttpServletRequest request){
		BaseResponse baseResponse = new BaseResponse();
		System.out.println(flightNo+"..."+flightDate);
		
		//HttpResponse response = null;
		String resultStr = "{\"output\":{\"result\":[{\"flightNo\":\"KN5987\",\"rate\":\"59.33\",\"depCity\":\"北京南苑\",\"depCode\":\"NAY\",\"arrCity\":\"上海浦东\",\"arrCode\":\"PVG\",\"depPort\":\"北京南苑机场\",\"arrPort\":\"上海浦东国际机场\",\"depTerminal\":\"\",\"arrTerminal\":\"T1\",\"depScheduled\":\"2018-09-30T20:50:00Z\",\"arrScheduled\":\"2018-09-30T23:15:00Z\",\"depEstimated\":\"0001-01-01T00:00:00Z\",\"arrEstimated\":\"0001-01-01T00:00:00Z\",\"depActual\":\"0001-01-01T00:00:00Z\",\"arrActual\":\"0001-01-01T00:00:00Z\",\"codeShares\":[]}]}}";
			Map<String, String> map = new HashMap<>();
			//resultStr = EntityUtils.toString(response.getEntity());
			JsonParser jp = new JsonParser();
			JsonObject jo = jp.parse(resultStr).getAsJsonObject();
			JsonArray resultJsonArray = jo.get("output").getAsJsonObject().get("result").getAsJsonArray();
			JsonObject result = jp.parse(resultJsonArray.get(0).toString()).getAsJsonObject();
			String depCity = result.get("depCity").getAsString();
			String arrCity = result.get("arrCity").getAsString();
			map.put("depCity", depCity);
			map.put("arrCity", arrCity);
		baseResponse.setData(map);
		return baseResponse;
	}

}
