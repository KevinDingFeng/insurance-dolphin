package com.shenghesun.controller.pay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.shenghesun.common.BaseResponse;
import com.shenghesun.entity.PayMessage;
import com.shenghesun.service.PayService;
import com.shenghesun.service.UserService;
import com.shenghesun.util.RandomUtil;
import com.shenghesun.util.wxpay.WXPay;
import com.shenghesun.util.wxpay.WXPayConfig;
import com.shenghesun.util.wxpay.WXPayConfigImpl;
import com.shenghesun.util.wxpay.WXPayUtil;

@RestController
@RequestMapping("/pay")
public class OrderController {
	@Autowired
	private PayService payService;
	
	/**通过openId获取预支付id
	 * * @Title: getPrePayId
	   * @Description: TODO
	   * @author dan
	   * @date 上午11:48:07
	   * @param o
	   * @return
	 * @throws Exception 
	 */
	@RequestMapping("/order")
	@ResponseBody
	public Object getPrePayId(HttpServletRequest request,PayMessage payMessage) throws Exception {
		//完善投保对象信息
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
		String orderNo = System.currentTimeMillis() + RandomUtil.randomString(2);
		payMessage.setOrderNo(orderNo);
		
		BaseResponse response = new BaseResponse();
		Map<String,String> map = new HashMap<>();
        map.put("openid",payMessage.getOpenid());//用户标识openId
        map.put("spbill_create_ip",request.getRemoteAddr());//请求Ip地址
        map.put("body", "飞行行李险-保险");//商品描述			body 商家名称-销售商品类目
        map.put("out_trade_no", orderNo);//商户订单号			out_trade_no
        map.put("total_fee", "1");//标价金额			total_fee
        map.put("trade_type", "JSAPI");//交易类型			trade_type
        WXPayConfig conf = new WXPayConfigImpl();
        WXPay wxPay = new WXPay(conf, "https://localhost:4455/wxpay/notify");
        Map<String,String> resultMap = wxPay.unifiedOrder(map);
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
             while (it.hasNext()) {
             Map.Entry<String, String> entry = it.next();
        }
          
        String returnCode = (String) resultMap.get("return_code");//通信标识
        String returnMsg = (String) resultMap.get("return_msg");//通信错误信息
        String resultCode = (String) resultMap.get("result_code");//交易标识
        
        
        String appId = (String) resultMap.get("appid");//微信公众号AppId
        String timeStamp = String.valueOf(System.currentTimeMillis()/1000);//当前时间戳
        String prepayId = "prepay_id="+resultMap.get("prepay_id");//统一下单返回的预支付id
        
        
        String nonceStr = RandomUtil.randomString(20);//不长于32位的随机字符串
        SortedMap<String, String> signMap = new TreeMap<>();//自然升序map
        signMap.put("appId",appId);
        signMap.put("package",prepayId);
        signMap.put("timeStamp",timeStamp);
        signMap.put("nonceStr",nonceStr);
        signMap.put("signType","MD5");
        JSONObject json = new JSONObject();
        String paySign = WXPayUtil.generateSignature(signMap, conf.getKey());
        
        //返回调用微信支付请求需要的相关数据
        json.put("appId",appId);
        json.put("timeStamp",timeStamp);
        json.put("nonceStr",nonceStr);
        json.put("package",prepayId);
        json.put("signType", "MD5");
        json.put("paySign",paySign);
        response.setData(json);
        
        payService.save(payMessage);
		
        return response;
	}
	
}
