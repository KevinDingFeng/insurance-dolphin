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
		payMessage.setOrderAmount(1);
		String mark = payMessage.getMarkString();
		JsonParser jp = new JsonParser();  
		JsonArray jo = jp.parse(mark).getAsJsonArray();
		List<Mark> markList = new ArrayList<>();
		
		for(int i = 0;i<jo.size();i++) {
			String result = jp.parse(jo.get(i).toString()).getAsJsonObject().get("mark").getAsString();
			//System.out.println(result);
			if(StringUtils.isNotEmpty(result)) {
				Mark m = new Mark();
				m.setMark(result);
				m.setPayMessage(payMessage);
				markList.add(m);
				//System.out.println(m);
			}
		}
		//设置保单总金额
		payMessage.setMark(markList);
		//System.out.println(payMessage.getMark().size());
		payMessage.setInsurancecardcode(payMessage.getApplycardcode());
		payMessage.setEffectdate(payMessage.getSaildate());
		payMessage.setInsurantname(payMessage.getApplyname());
		
		if(payMessage.getClassestype().equals("1")) {
			payMessage.setClasstype("11040400");
			payMessage.setPremium(10);
			payMessage.setInvamount(1000);
			payMessage.setAmount(1000);
		}else {
			payMessage.setClasstype("11040400");
			payMessage.setPremium(20);
			payMessage.setInvamount(2000);
			payMessage.setAmount(2000);
		}
		return payMessage;
	}
}
