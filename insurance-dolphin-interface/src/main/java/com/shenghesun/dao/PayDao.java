package com.shenghesun.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shenghesun.entity.PayMessage;

@Repository
public interface PayDao extends PagingAndSortingRepository<PayMessage, Long>, JpaSpecificationExecutor<PayMessage>{

}
