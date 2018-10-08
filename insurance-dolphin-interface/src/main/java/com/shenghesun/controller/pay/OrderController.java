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
	public Object getPrePayId(HttpServletRequest request,String openId,String total_fee) throws Exception {
		System.out.println("支付");
		BaseResponse response = new BaseResponse();
		Map<String,String> map = new HashMap<>();
		//"o8ood1Eo3QzMT1JyxqXtE9Xv_QR0"
        map.put("openid",openId);//用户标识openId
        map.put("spbill_create_ip",request.getRemoteAddr());//请求Ip地址
        map.put("body", "飞行行李险-保险");//商品描述			body 商家名称-销售商品类目
        map.put("out_trade_no", System.currentTimeMillis() + RandomUtil.randomString(2));//商户订单号			out_trade_no
        map.put("total_fee", "2");//标价金额			total_fee
        map.put("trade_type", "JSAPI");//交易类型			trade_type
        WXPayConfig conf = new WXPayConfigImpl();
        WXPay wxPay = new WXPay(conf, "https://localhost:4455/wxpay/notify");
        Map<String,String> resultMap = wxPay.unifiedOrder(map);
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
             while (it.hasNext()) {
             Map.Entry<String, String> entry = it.next();
           System.out.println("key= =====" + entry.getKey() + " and value= =====" + entry.getValue());
        }

        
        String returnCode = (String) resultMap.get("return_code");//通信标识
        String returnMsg = (String) resultMap.get("return_msg");//通信错误信息
        String resultCode = (String) resultMap.get("result_code");//交易标识
        
        System.out.println("returnCode........"+returnCode);
        System.out.println("returnMsg........."+returnMsg);
        System.out.println("resultCode........"+resultCode);
        
        String appId = (String) resultMap.get("appid");//微信公众号AppId
        String timeStamp = String.valueOf(System.currentTimeMillis()/1000);//当前时间戳
        String prepayId = "prepay_id="+resultMap.get("prepay_id");//统一下单返回的预支付id
        
        System.out.println("prepayId.........."+prepayId);
        
        String nonceStr = RandomUtil.randomString(20);//不长于32位的随机字符串
        SortedMap<String, String> signMap = new TreeMap<>();//自然升序map
        signMap.put("appId",appId);
        signMap.put("package",prepayId);
        signMap.put("timeStamp",timeStamp);
        signMap.put("nonceStr",nonceStr);
        signMap.put("signType","MD5");
        System.out.println("conf  getkey............................"+conf.getKey());
        JSONObject json = new JSONObject();
        String paySign = WXPayUtil.generateSignature(signMap, conf.getKey());
        
        System.out.println("paySign"+",,,,,,,,,,"+paySign);
        json.put("appId",appId);
        json.put("timeStamp",timeStamp);
        json.put("nonceStr",nonceStr);
        json.put("package",prepayId);
        json.put("signType", "MD5");
        json.put("paySign",paySign);
        response.setData(json);
        
		return response;
	}
	
}
