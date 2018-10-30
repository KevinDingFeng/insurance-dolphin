package com.shenghesun.service.cpic;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.shenghesun.entity.Mark;
import com.shenghesun.entity.PayMessage;
import com.shenghesun.model.webservice.Datas;
import com.shenghesun.model.webservice.Freightcpic;
import com.shenghesun.model.webservice.Header;
import com.shenghesun.service.PayService;
import com.shenghesun.util.StringGenerateUtils;
import com.shenghesun.util.XStreamUtil;

/**
  * 异步方法
  * @ClassName: AsyncService 
  * @Description: TODO
  * @author: yangzp
  * @date: 2018年10月10日 下午7:04:32  
  */
@Service
public class AsyncService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PayService payService;
	
	@Autowired
	private WebServiceClient webServiceClient;
	
//	@Autowired
//	private SmsCodeService smsCodeService;
	
	@Async("asyncServiceExecutor")
    public void executeAsync(PayMessage payMessage) {
        //logger.info("start executeAsync");
        try{
//        	PayMessage payMessage = payService.findByOrderNo(orderNo);
    		
    		//修改保单状态
    		payMessage.setPayStatus(1);
    		payService.save(payMessage);
    		//测试用，正式删除
//    		payMessage.setOrderNo(StringGenerateUtils.generateId());
    		
    		if(payMessage != null) {
    			PayMessage pmTemp = new PayMessage();
    			BeanUtils.copyProperties(payMessage, pmTemp);
    			pmTemp.setId(null);
    			pmTemp.setCreation(null);
    			pmTemp.setLastModified(null);
    			pmTemp.setVersion(null);
    			List<Mark> markList = pmTemp.getMark();
    			if(!CollectionUtils.isEmpty(markList)) {
    				boolean flag = true;
    				for(Mark mark : markList) {
    					String xml = payMessage2Xml(pmTemp,mark.getMark());
            			if(StringUtils.isNotEmpty(xml)) {
            				//货运险承保接口
            				flag = webServiceClient.approvl(xml,payMessage);
            				if(!flag) {
            					flag = false;
            				}
            			}
    				}
//    				String smsStatus;
    				if(flag) {//保单生效
    					//发送成功短信
//					    smsStatus = smsCodeService.sendSmsCode(payMessage.getInsuranttel(), payMessage.getOrderNo());
//						if("success".equals(smsStatus)) {
//							logger.info("订单号为:"+payMessage.getOrderNo()+"的订单短信通知成功");
//						}else {
//							logger.info("订单号为:"+payMessage.getOrderNo()+"的订单短信通知失败");
//						}
    					logger.info("订单号为:"+payMessage.getOrderNo()+"的订单投保成功");
    				}else {
						//发送失败短信
//						smsStatus = smsCodeService.sendSmsCode(payMessage.getInsuranttel(), "飞行行李险下单失败！");
//						if("success".equals(smsStatus)) {
//							logger.info("订单号为:"+payMessage.getOrderNo()+"的订单失败短信通知成功");
//						}else {
//							logger.info("订单号为:"+payMessage.getOrderNo()+"的订单短信通知失败");
//						}
    					logger.info("订单号为:"+payMessage.getOrderNo()+"的订单投保失败");
    				}
    			}
    			
    		}
        }catch(Exception e){
            e.printStackTrace();
            logger.error("Exception {} in {}", e.getStackTrace(), Thread.currentThread().getName());
        }
        //logger.info("end executeAsync");
    }
	
	/**
	 * 货运险承保接口应答报文转xml
	 * @Title: payMessage2Xml 
	 * @Description: TODO 
	 * @param payMessage
	 * @return  String 
	 * @author yangzp
	 * @date 2018年10月10日下午5:36:20
	 **/ 
	private String payMessage2Xml(PayMessage payMessage, String markNo) {
		Freightcpic freightcpic = new Freightcpic();
		
		Header header = new Header();
		header.setApplyid(StringGenerateUtils.generateId());
		header.setClassestype(payMessage.getClassestype());
		freightcpic.setHeader(header);
		
		Datas datas = new Datas();
		payMessage.setMarkNo(markNo);
//		if("2".equals(payMessage.getClassestype())) {
//			payMessage.setFlightareacode("12040200");
//		}
		datas.setPayMessage(payMessage);
		
		freightcpic.setDatas(datas);
		
		return XStreamUtil.beanToXmlWithTag(freightcpic);
	}
}
