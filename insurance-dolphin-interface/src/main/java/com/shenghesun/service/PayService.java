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
   
	public PayMessage findByOrderNo(String orderNo) {
		return payDao.findByOrderNo(orderNo);
	}
	public PayMessage completePayMessage(PayMessage payMessage) {
		payMessage.setInsurancecardcode(payMessage.getApplycardcode());
		payMessage.setEffectdate(payMessage.getSaildate());
		payMessage.setInsurantname(payMessage.getApplyname());
		payMessage.setInvamount(payMessage.getAmount());
		payMessage.setPremium((int) (payMessage.getAmount()*payMessage.getRate()));
		if(payMessage.getClassestype().equals("1")) {
			payMessage.setClasstype("11040400");
		}else {
			payMessage.setClasstype("12040200");
		}
		return payMessage;
	}
}
