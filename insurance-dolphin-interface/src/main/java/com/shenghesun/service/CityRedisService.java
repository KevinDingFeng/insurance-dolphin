package com.shenghesun.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.shenghesun.entity.CityCode;
import com.shenghesun.util.RedisUtil;

@Component
@Order(value = 1)
public class CityRedisService implements ApplicationRunner{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private CityService cityService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<CityCode> cityCodes = cityService.findAll();
		if(!CollectionUtils.isEmpty(cityCodes)) {
			logger.info("redis缓存城市代码开始===条数为:"+cityCodes.size());
			long start = System.currentTimeMillis();
			for(int i=0;i<cityCodes.size();i++) {
				CityCode cc = cityCodes.get(i);
				String key = "cityCode:id:"+cc.getId()+":cityCode:" + cc.getCityCode() + ":cityName:" + cc.getCityName();
				redisUtil.set(cc.getCityCode(), cc);
				redisUtil.set(key, cc);
			}
			long end = System.currentTimeMillis(); 
			logger.info("redis缓存城市代码结束===运行时间:"+(end - start)+"毫秒");
		}
	}

}
