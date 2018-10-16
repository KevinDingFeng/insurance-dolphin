package com.shenghesun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenghesun.dao.CityDao;
import com.shenghesun.entity.CityCode;

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
	public CityCode findByCityNameLike(String name) {
		try {
			CityCode cityCode = cityDao.findByCityNameLike(name).get(0);
			return cityCode;
		} catch (Exception e) {
			return null;
		}	
	}
	public List<CityCode> findAll(){
		return (List<CityCode>) cityDao.findAll();
	}
}
