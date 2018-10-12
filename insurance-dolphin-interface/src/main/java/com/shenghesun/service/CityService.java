package com.shenghesun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenghesun.dao.CityDao;
import com.shenghesun.dao.UserDao;
import com.shenghesun.entity.CityCode;
import com.shenghesun.entity.PayMessage;

@Service
public class CityService {
	@Autowired
	private CityDao cityDao;
	
	public CityCode findByCityCode(String code) {
		return cityDao.findByCityCode(code);
	}
	public CityCode findByCityName(String name) {
		return cityDao.findByCityName(name);
	}
}
