package com.shenghesun.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.shenghesun.dao.PayDao;
import com.shenghesun.entity.Mark;
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
		String mark[] = payMessage.getMarkString();
		List<Mark> markList = new ArrayList<>();
		for(int i = 0;i<mark.length;i++) {
			if(StringUtils.isNotEmpty(mark[i])) {
				Mark m = new Mark();
				m.setMark(mark[i]);
				m.setPayMessage(payMessage);
				markList.add(m);
			}
		}
		//设置保单总金额
		payMessage.setMark(markList);
		//System.out.println(payMessage.getMark().size());
		payMessage.setInsurancecardcode(payMessage.getApplycardcode());
		payMessage.setEffectdate(payMessage.getSaildate());
		payMessage.setInsurantname(payMessage.getApplyname());
		
		if(payMessage.getClassestype().equals("1")) {//国内
			payMessage.setClasstype("11040400");
			payMessage.setPremium(10);
			payMessage.setInvamount(1000);
			payMessage.setAmount(1000);
			//国内清空“航行区域代码”
			payMessage.setFlightareacode(null);
			//国内清空“理赔代理地代码”
			payMessage.setClaimagent(null);
		}else {//国际
			payMessage.setClasstype("11040400");
			payMessage.setPremium(20);
			payMessage.setInvamount(2000);
			payMessage.setAmount(2000);
		}
		return payMessage;
	}
}
