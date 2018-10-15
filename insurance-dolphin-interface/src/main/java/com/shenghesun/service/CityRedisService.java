package com.shenghesun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.shenghesun.entity.CityCode;
import com.shenghesun.util.RedisUtil;

@Component
@Order(value = 1)
public class CityRedisService implements ApplicationRunner{
	
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private CityService cityService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<CityCode> cityCode = cityService.findAll();
		for(int i=0;i<cityCode.size();i++) {
			redisUtil.set(cityCode.get(i).getCityCode(), cityCode.get(i));
			redisUtil.set(cityCode.get(i).getCityName(), cityCode.get(i));
		}	
	}

}
