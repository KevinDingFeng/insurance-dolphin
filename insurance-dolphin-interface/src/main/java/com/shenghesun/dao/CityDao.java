package com.shenghesun.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shenghesun.entity.CityCode;

@Repository
public interface CityDao extends PagingAndSortingRepository<CityCode, Long>, JpaSpecificationExecutor<CityCode>{
	
	public CityCode findByCityCode(String code);
	
	public CityCode findByCityName(String name);
	
	public List<CityCode> findByCityNameLike(String name);
}
