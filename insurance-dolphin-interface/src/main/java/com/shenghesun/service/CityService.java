package com.shenghesun.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shenghesun.common.BaseResponse;
import com.shenghesun.dao.CityDao;
import com.shenghesun.entity.City;
import com.shenghesun.entity.CityCode;
import com.shenghesun.util.RedisUtil;

@Service
public class CityService {
	//private final Logger logger = LoggerFactory.getLogger(this.getClass());
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
		return cityList.get(0);
	}
	public List<CityCode> findAll(){
		return (List<CityCode>) cityDao.findAll();
	}
	//扫描登机牌二维码获取城市信息
	public Map<String, String> getFlightMessage(City city){
		String depCityCode = city.getDepCityCode();
		String arrCityCode = city.getArrCityCode();
		CityCode depCity = null;
		CityCode arrCity = null;
		if(arrCityCode!=null&&depCityCode!=null) {
			//查询redis中depCityCode始发站三字代码
			if(redisUtil.exists(depCityCode)){
				depCity = getCity(depCityCode);
			}
			//查询redis中arrCityCode终点站三字代码
			if(redisUtil.exists(arrCityCode)){
				arrCity = getCity(arrCityCode);
			}
		}
		Map<String, String> map = setReturnMap(depCity,arrCity);
		return map;
	}
	
	
	//手动输入城市获取城市信息
	public Map<String, String> getCityMessage(City city) {
		String depCityName = city.getDepCity();
		String arrCityName = city.getArrCity();
		CityCode depCity = null;
		CityCode arrCity = null;
		//手动输入城市获取国际还是国外
		//从redis查询depCity
		Set<Object> depkeys = redisUtil.keys("cityCode:*cityName:*"+depCityName+"*");
		depCity = returnCity(depkeys);
		//从redis查询arrCity
		Set<Object> arrkeys = redisUtil.keys("cityCode:*cityName:*"+arrCityName+"*");
		arrCity = returnCity(arrkeys);
		Map<String, String> map = setReturnMap(depCity,arrCity);
		return map;
	}
	//根据Set集合返回城市信息
	public CityCode returnCity(Set<Object> keys) {
		Iterator<Object> it = keys.iterator();  
		String c = null;
		CityCode city = null;
		while (it.hasNext()) {  
			c = (String)it.next();
		}  
		if(c!=null) {
			city = getCity(c);
		}
		return city;
	}
	//根据城市名称或者三字代码返回城市信息
	public CityCode getCity(String key) {
		String cityMessage = (String) redisUtil.get(key);
		CityCode cc = null;
		try {
			cc = JSON.parseObject(cityMessage, CityCode.class);
		} catch (Exception e) {
			cc = findByCityNameLike("%"+key+"%");
			if(cc!=null) {
				setToRedis(cc,key);
			}
		}
		return cc;
	}

	//将数据存放redis
	public void setToRedis(CityCode city,String key) {
		if(city!=null) {
			String json = JSONObject.toJSONString(city);
			redisUtil.set(key, json,BaseResponse.redis_ex);
		}	
	}
	
	//返回map存放数据
	public Map<String, String> setReturnMap(CityCode depCity,CityCode arrCity){
		Map<String, String> map = new HashMap<>();	
		if(depCity!=null && arrCity!=null) {
			map.put("depCity", depCity.getCityName());
			map.put("arrCity", arrCity.getCityName());
			if(depCity.getCityType().equals("2")||arrCity.getCityType().equals("2")) {
				map.put("classtype", BaseResponse.foreign_type);
				map.put("total_fee", BaseResponse.foreign_price);
				map.put("del_price", BaseResponse.preForeign_price);
			}else {
				map.put("classtype", BaseResponse.inner_type);
				map.put("total_fee", BaseResponse.inner_price);
				map.put("del_price", BaseResponse.preInner_price);
			}
		}else {
			if(depCity==null) {
				map.put("total_fee", BaseResponse.error_price);
			}else {
				map.put("total_fee",  BaseResponse.error_price1);
			}
		}
		return map;
	}
}
