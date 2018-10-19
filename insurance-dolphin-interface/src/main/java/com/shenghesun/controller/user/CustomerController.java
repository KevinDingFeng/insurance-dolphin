package com.shenghesun.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shenghesun.common.BaseResponse;
import com.shenghesun.entity.City;
import com.shenghesun.service.CityService;


@RestController
@RequestMapping("/customer")
class CustomerController {
	
	@Autowired
	private CityService cityService;

	/**
	 * 扫描登机牌，获取城市起止信息和价格信息
	 * @param city
	 * @return
	 */
	@GetMapping("getFlight")
	@ResponseBody
	public Object getCity(City city){
		BaseResponse baseResponse = new BaseResponse();
		Map<String, String> map = cityService.getFlightMessage(city);
		baseResponse.setData(map);
		return baseResponse;
	}
	/**
	 * 手动输入城市，获取价格
	 * @param city
	 * @return
	 */
	@GetMapping("getPrice")
	@ResponseBody
	public Object getPrice(City city){
		BaseResponse baseResponse = new BaseResponse();
		Map<String, String> map = cityService.getCityMessage(city);
		baseResponse.setData(map);
		return baseResponse;
	}
	
}
