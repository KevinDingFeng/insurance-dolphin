package com.shenghesun.service.cpic;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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
	
	@Async("asyncServiceExecutor")
    public void executeAsync(String orderNo) {
        //logger.info("start executeAsync");
        try{
        	PayMessage payMessage = payService.findByOrderNo(orderNo);
    		
    		//修改保单状态
    		payMessage.setPayStatus(1);
    		payService.save(payMessage);
    		//测试用，正式删除
    		payMessage.setOrderNo(StringGenerateUtils.generateId());
    		if(payMessage != null) {
    			String xml = payMessage2Xml(payMessage);
    			if(StringUtils.isNotEmpty(xml)) {
    				//货运险承保接口
    				webServiceClient.approvl(xml);
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
	private String payMessage2Xml(PayMessage payMessage) {
		Freightcpic freightcpic = new Freightcpic();
		
		Header header = new Header();
		header.setApplyid(payMessage.getOrderNo());
		header.setClassestype(payMessage.getClasstype());
		freightcpic.setHeader(header);
		
		Datas datas = new Datas();
		datas.setPayMessage(payMessage);
		
		freightcpic.setDatas(datas);
		
		return XStreamUtil.beanToXmlWithTag(freightcpic);
	}
}
