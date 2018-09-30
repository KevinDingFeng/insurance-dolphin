package com.shenghesun.controller.user;

import java.rmi.MarshalledObject;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shenghesun.common.BaseResponse;
import com.shenghesun.service.UserService;
import com.shenghesun.util.HttpUtils;
import com.shenghesun.util.SmsCodeService;



@RestController
@RequestMapping("/customer")
class CustomerController {

	@Autowired
	private SmsCodeService sendSmsCode;

	/**通过手机号获取验证码
	 * * @Title: getCode
	 * @Description: TODO
	 * @author dan
	 * @date 上午11:48:41
	 * @param telNumber
	 * @return
	 */
	@GetMapping("telCode")
	@ResponseBody
	public Object getCode(String telNumber){
		BaseResponse response = new BaseResponse();
		try {
			sendSmsCode.sendSmsCode("15776583901", "abcdefg", "SMS_001");
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(telNumber);
		return response;

	}

	/**通过航班号获取起始地信息
	 * * @Title: login
	 * @Description: TODO
	 * @author dan
	 * @date 上午11:48:54
	 * @param telNumber
	 * @return
	 */
	@GetMapping("flight1")
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
		String bodys = "{\"serviceID\":\"urn:cdif-io:serviceID:航班信息查询服务\",\"actionName\":\"起降时间查询\",\"input\":{\"flightNo\":\"KN5987\",\"date\":\"2018-09-30\"}}";
		//完整请求示例     curl-H'Authorization:APPCODE你自己的AppCode'-H'Content-Type:application/json'-XPOST-d'{\"serviceID\":\"urn:cdif-io:serviceID:航班信息查询服务\",\"actionName\":\"起降时间查询\",\"input\":{\"flightNo\":\"SC4602\",\"date\":\"2017-05-20\"}}'http://apemesh.market.alicloudapi.com/devices/055a2472-fd32-595f-add1-726f9987d4d3/invoke-action
		HttpResponse response = null;
		String resultStr = null;
		try {

			response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			//HttpResponse response = HttpUtils.doPost("http://www.baidu.com", "", "POST", headers, querys, bodys);
			int status = response.getStatusLine().getStatusCode();
			if ((status >= 200) && (status < 300)) {
				Header[] rheaders = response.getAllHeaders();
				System.out.println(EntityUtils.toString(response.getEntity()));
			}
			resultStr = EntityUtils.toString(response.getEntity());
			JsonParser jp = new JsonParser();
			JsonObject jo = jp.parse(resultStr).getAsJsonObject();
			JsonArray resultJsonArray = jo.get("output").getAsJsonObject().get("result").getAsJsonArray();
			JsonObject result = jp.parse(resultJsonArray.get(0).toString()).getAsJsonObject();
			String depCity = result.get("depCity").getAsString();
			String arrCity = result.get("arrCity").getAsString();
			System.out.println(depCity);
			System.out.println(arrCity);
			System.out.println(resultJsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		baseResponse.setData(resultStr);
		return baseResponse;



	}

}
