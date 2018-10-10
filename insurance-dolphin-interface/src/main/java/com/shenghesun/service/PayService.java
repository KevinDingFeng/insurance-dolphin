package com.shenghesun.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenghesun.dao.PayDao;
import com.shenghesun.entity.PayMessage;


@Service
public class PayService {
	@Autowired
	private PayDao payDao;
	
	public PayMessage save(PayMessage payMessage) {
		return payDao.save(payMessage);
	}
   
}
