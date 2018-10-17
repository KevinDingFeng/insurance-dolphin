package com.shenghesun.controller.user;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.shenghesun.common.BaseResponse;
import com.shenghesun.entity.City;
import com.shenghesun.entity.CityCode;
import com.shenghesun.service.CityService;
import com.shenghesun.util.RedisUtil;


@RestController
@RequestMapping("/customer")
class CustomerController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CityService cityService;
	@Autowired
	private RedisUtil redisUtil;

	@GetMapping("getFlight")
	@ResponseBody
	public Object getFlightNo(City city){
		BaseResponse baseResponse = new BaseResponse();
		Map<String, String> map = new HashMap<>();	
		String depCityCode = city.getDepCityCode();
		String arrCityCode = city.getArrCityCode();
		String depCityName = city.getDepCity();
		String arrCityName = city.getArrCity();
		CityCode depCity = null;
		CityCode arrCity = null;
		//扫描登机牌二维码获取城市信息
		if(city.getArrCityCode()!=null&&city.getDepCityCode()!=null) {
			//判断redis中是否存在depCityCode
			if(redisUtil.exists(depCityCode)){
				String dep = (String) redisUtil.getObj(depCityCode);
				depCity = JSON.parseObject(dep, CityCode.class);
			}else {
				depCity = cityService.findByCityCode(depCityCode);
				if(depCity!=null) {
					String json = JSON.toJSONString(depCity, true);
					redisUtil.set(depCityCode, depCity);
				}	
			}
			//判断redis中是否存在arrCity
			if(redisUtil.exists(arrCityCode)){
				String arr = (String) redisUtil.getObj(arrCityCode);
				arrCity = JSON.parseObject(arr, CityCode.class);
			}else {
				arrCity = cityService.findByCityCode(arrCityCode);
				if(arrCity!=null) {
					String json = JSON.toJSONString(arrCity, true); 
					redisUtil.set(arrCityCode, arrCity);
				}
			}
			map.put("depCity", depCity.getCityName());
			map.put("arrCity", arrCity.getCityName());
			if(depCity.getCityType().equals("2")||arrCity.getCityType().equals("2")) {
				System.out.println("国际航班");
				map.put("total_fee", "2000");
				map.put("classtype", "2");
			}else {
				System.out.println("国内航班");
				map.put("total_fee", "1000");
				map.put("classtype", "1");
			}
		}else {
			//手动输入城市获取国际还是国外
			//判断redis中是否存在depCity
			if(redisUtil.exists(depCityName)){
				String dep = (String) redisUtil.getObj(depCityName);
				try {
					depCity = JSON.parseObject(dep, CityCode.class);
				} catch (Exception e) {
					logger.error("城市代码解析出错，错误城市名称："+depCityName);
					return baseResponse;
				}
			}else {
				depCity = cityService.findByCityNameLike("%"+city.getDepCity()+"%");
				if(depCity!=null) {
					String json = JSON.toJSONString(depCity, true); 
					redisUtil.set(depCityName, json);
				}	
			}
			//判断redis中是否存在arrCity
			if(redisUtil.exists(arrCityName)){
				String dep = (String) redisUtil.getObj(arrCityName);
				try {
					arrCity = JSON.parseObject(dep, CityCode.class);
				} catch (Exception e) {
					logger.error("城市代码解析出错，错误城市名称："+arrCityName);
					return baseResponse;
				}
			}else {
				arrCity = cityService.findByCityNameLike("%"+city.getArrCity()+"%");
				if(arrCity!=null) {
					String json = JSON.toJSONString(arrCity, true); 
					redisUtil.set(arrCityName, json);
				}	
			}
			if(depCity!=null && arrCity!=null) {
				if(depCity.getCityType().equals("2")||arrCity.getCityType().equals("2")) {
					System.out.println("国际航班");
					map.put("classtype", "2");
					map.put("total_fee", "20");
				}else {
					System.out.println("国内航班");
					map.put("classtype", "1");
					map.put("total_fee", "10");
				}
			}		
		}
		baseResponse.setData(map);
		return baseResponse;
	}


}
