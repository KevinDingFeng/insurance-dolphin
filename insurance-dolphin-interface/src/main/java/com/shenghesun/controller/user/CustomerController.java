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

	@GetMapping("getFlight")
	@ResponseBody
	public Object getFlightNo(City city){
		BaseResponse baseResponse = new BaseResponse();
		Map<String, String> map = cityService.getFlightMessage(city);
		baseResponse.setData(map);
		return baseResponse;
	}
}
