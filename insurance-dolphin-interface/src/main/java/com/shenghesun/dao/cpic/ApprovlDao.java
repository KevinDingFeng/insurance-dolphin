package com.shenghesun.dao.cpic;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shenghesun.entity.cpic.Approvl;

@Repository
public interface ApprovlDao extends PagingAndSortingRepository<Approvl, Long>, JpaSpecificationExecutor<Approvl>{
	
	public Approvl findByApplyId(String applyId);
}
