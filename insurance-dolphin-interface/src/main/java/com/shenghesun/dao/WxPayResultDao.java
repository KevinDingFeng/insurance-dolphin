package com.shenghesun.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shenghesun.entity.User;
import com.shenghesun.entity.WxPayResult;
@Repository
public interface WxPayResultDao extends PagingAndSortingRepository<WxPayResult, Long>, JpaSpecificationExecutor<User>{

}
