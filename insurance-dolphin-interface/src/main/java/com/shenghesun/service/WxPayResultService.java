package com.shenghesun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenghesun.dao.WxPayResultDao;
import com.shenghesun.entity.WxPayResult;

@Service
public class WxPayResultService {

	@Autowired
	WxPayResultDao wxPayDao;
	
	public WxPayResult save(WxPayResult wxPay) {
		return wxPayDao.save(wxPay);
	}
}
