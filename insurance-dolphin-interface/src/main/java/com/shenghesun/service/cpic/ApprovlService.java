package com.shenghesun.service.cpic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenghesun.dao.cpic.ApprovlDao;
import com.shenghesun.entity.cpic.Approvl;

@Service
public class ApprovlService {

	@Autowired
	private ApprovlDao approvlDao;
	
	public Approvl save(Approvl approvl) {
		return approvlDao.save(approvl);
	}
	
	public Approvl findByApplyId(String applyId) {
		return approvlDao.findByApplyId(applyId);
	}
}
