package com.shenghesun.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.shenghesun.dao.CityDao;
import com.shenghesun.entity.City;
import com.shenghesun.entity.CityCode;
import com.shenghesun.util.RedisUtil;

@Service
public class CityService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CityDao cityDao;
	@Autowired
	private RedisUtil redisUtil;
	
	public CityCode findByCityCode(String code) {
		return cityDao.findByCityCode(code);
	}
	public CityCode findByCityName(String name) {
		return cityDao.findByCityName(name);
	}
	public CityCode findByCityNameLike(String name) {
		List<CityCode> cityList = cityDao.findByCityNameLike(name);
		if (CollectionUtils.isEmpty(cityList)) {
			return null;
		}
		// CityCode cityCode = cityDao.findByCityNameLike(name).get(0);
		return cityList.get(0);
	}
	public List<CityCode> findAll(){
		return (List<CityCode>) cityDao.findAll();
	}
	
	public Map<String, String> getFlightMessage(City city){
		Map<String, String> map = new HashMap<>();	
		String depCityCode = city.getDepCityCode();
		String arrCityCode = city.getArrCityCode();
		String depCityName = city.getDepCity();
		String arrCityName = city.getArrCity();
		CityCode depCity = null;
		CityCode arrCity = null;
		//扫描登机牌二维码获取城市信息
		if(arrCityCode!=null&&depCityCode!=null) {
			//判断redis中是否存在depCityCode始发站三字代码
			if(redisUtil.exists(depCityCode)){
				String dep = (String) redisUtil.get(depCityCode);
				depCity = JSON.parseObject(dep, CityCode.class);
			}else {
				depCity = this.findByCityCode(depCityCode);
				if(depCity!=null) {
					String json = JSON.toJSONString(depCity, true);
					redisUtil.set(depCityCode, json);
				}	
			}
			//判断redis中是否存在arrCity终点站三字代码
			if(redisUtil.exists(arrCityCode)){
				String arr = (String) redisUtil.get(arrCityCode);
				arrCity = JSON.parseObject(arr, CityCode.class);
			}else {
				arrCity = this.findByCityCode(arrCityCode);
				if(arrCity!=null) {
					String json = JSON.toJSONString(arrCity, true); 
					redisUtil.set(arrCityCode, json);
				}
			}
			if(depCity!=null && arrCity!=null) {
				map.put("depCity", depCity.getCityName());
				map.put("arrCity", arrCity.getCityName());
				if(depCity.getCityType().equals("2")||arrCity.getCityType().equals("2")) {
					System.out.println("国际航班");
					map.put("total_fee", "28");
					map.put("classtype", "2");
				}else {
					System.out.println("国内航班");
					map.put("total_fee", "15");
					map.put("classtype", "1");
				}
			}else {
				if(depCity==null) {
					map.put("total_fee", "00");
					logger.info("航班三字代码不存在："+depCityCode);
				}else {
					map.put("total_fee", "01");
					logger.info("航班三字代码不存在："+arrCityCode);
				}
			}
		
		}else{
			//手动输入城市获取国际还是国外
			//判断redis中是否存在depCity
			if(redisUtil.exists(depCityName)){
				String dep = (String) redisUtil.get(depCityName);
				try {
					depCity = JSON.parseObject(dep, CityCode.class);
					logger.info("redis查询"+depCityName);
					logger.info(dep);
				} catch (Exception e) {
					depCity = this.findByCityNameLike("%"+city.getDepCity()+"%");
					if(depCity!=null) {
						String json = JSON.toJSONString(depCity, true); 
						redisUtil.set(depCityName, json);
					}	
					logger.error("城市代码解析出错，错误城市名称："+depCityName);
				}
			}else {
				depCity = this.findByCityNameLike("%"+depCityName+"%");
				logger.info("数据库查询117:"+depCityName);
				if(depCity!=null) {
					String json = JSON.toJSONString(depCity, true); 
					redisUtil.set(depCityName, json);
					logger.info(json);
				}	
			}
			//判断redis中是否存在arrCity
			if(redisUtil.exists(arrCityName)){
				String dep = (String) redisUtil.get(arrCityName);
				try {
					arrCity = JSON.parseObject(dep, CityCode.class);
				} catch (Exception e) {
					arrCity = this.findByCityNameLike("%"+city.getArrCity()+"%");
					if(arrCity!=null) {
						String json = JSON.toJSONString(arrCity, true); 
						redisUtil.set(arrCityName, json);
					}	
					logger.error("城市代码解析出错，错误城市名称："+arrCityName);
				}
			}else {
				arrCity = this.findByCityNameLike("%"+city.getArrCity()+"%");
				if(arrCity!=null) {
					String json = JSON.toJSONString(arrCity, true); 
					redisUtil.set(arrCityName, json);
				}	
			}
			if(depCity!=null && arrCity!=null) {
				if(depCity.getCityType().equals("2")||arrCity.getCityType().equals("2")) {
					System.out.println("国际航班");
					map.put("classtype", "2");
					map.put("total_fee", "28");
				}else {
					System.out.println("国内航班");
					map.put("classtype", "1");
					map.put("total_fee", "15");
				}
			}else {
				if(depCity==null) {
					map.put("total_fee", "00");
					logger.info("用户输入城市不存在,城市名称为："+depCityName);
				}else {
					map.put("total_fee", "01");
					logger.info("用户输入城市不存在,城市名称为："+arrCityName);
				}
			}
			
		}
		return map;
	}
}
