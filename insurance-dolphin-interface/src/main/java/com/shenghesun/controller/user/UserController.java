package com.shenghesun.controller.user;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shenghesun.common.BaseResponse;

import com.shenghesun.entity.User;
import com.shenghesun.service.UserService;
import com.shenghesun.util.RedisUtil;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import me.chanjar.weixin.common.exception.WxErrorException;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	@Autowired
	private RedisUtil redisUtil;
    @Autowired
    private WxMaService wxService;

	
	/**
	 * 登陆接口
	 * 
	 * @throws WxErrorException
	 */
	@GetMapping("login")
	@ResponseBody
	public Object login(String code, String signature, String rawData, String encryptedData, String iv)
			throws WxErrorException {
		
		BaseResponse response = new BaseResponse();
		WxMaJscode2SessionResult session = this.wxService.getUserService().getSessionInfo(code);
		String sessionKey = session.getSessionKey();
		//通过openId sessionKey 生成3rd session 返回给客户端小程序
		String accessToken = UUID.randomUUID().toString();
		redisUtil.set(accessToken, sessionKey + ":" + session.getOpenid(), BaseResponse.ex);
		// 用户信息校验
        if (!this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
        	response.setErrorCode(BaseResponse.user_check_failed_code);
        	response.setMessage("用户信息校验失败");
            return response;
        }
        // 解密用户信息
        WxMaUserInfo userInfo = this.wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        Map<String, Object> data = new HashMap<>();
        data.put("accessToken", accessToken);
        //往mysql 中插入user 信息 插入前判断是否已经存在， 存在则进行更新
        User user = userService.findByOpenId(userInfo.getOpenId());
        if(user == null) {
        	user = new User();
            BeanUtils.copyProperties(userInfo, user);
            User dbUser = userService.save(user);
            data.put("userId", dbUser.getId());
            
        }else {
        	if(StringUtils.isEmpty(user.getNickName())) {
        		BeanUtils.copyProperties(userInfo, user);
                userService.save(user);
        	}
        	data.put("userId", user.getId());
        	data.put("openId", user.getOpenId());
        }
        
        response.setData(data);
		return response;
	}
	
	/**
	 * 无授权登陆
	 * @Title: unauthorizedLogin 
	 * @Description: TODO 
	 * @param code
	 * @return
	 * @throws WxErrorException  Object 
	 * @author yangzp
	 * @date 2018年9月12日下午6:20:43
	 **/ 
	@GetMapping("unauthorizedLogin")
	@ResponseBody
	public Object unauthorizedLogin(String code) throws WxErrorException {
		
		BaseResponse response = new BaseResponse();
		WxMaJscode2SessionResult session = this.wxService.getUserService().getSessionInfo(code);
		//String sessionKey = session.getSessionKey();
		//通过openId sessionKey 生成3rd session 返回给客户端小程序
		String accessToken = UUID.randomUUID().toString();
		//redisUtil.set(accessToken, sessionKey + ":" + session.getOpenid(), BaseResponse.ex);
        Map<String, Object> data = new HashMap<>();
        data.put("accessToken", accessToken);
        //往mysql 中插入user 信息 插入前判断是否已经存在， 存在则进行更新
        User user = userService.findByOpenId(session.getOpenid());
        if(user == null) {
        	user = new User();
        	user.setOpenId(session.getOpenid());
            User dbUser = userService.save(user);
            data.put("userId", dbUser.getId());
        }else {
        	data.put("userId", user.getId());
        }
        
        response.setData(data);
		return response;
	}
	
	
	@GetMapping("info")
	@ResponseBody
	public Object info(String token) {
		BaseResponse response = new BaseResponse();
		User user = userService.findByOpenId(getOpenId(token));
		Map<String, Object> data = new HashMap<>();
		data.put("myInfo", user);
		response.setData(data);
		return response;
	}
	
	
	@GetMapping("checkToken")
	@ResponseBody
	public Object checkToken(String token){
		BaseResponse response = new BaseResponse();
		//TODO token 没过期 并且有对应的用户
		if(!redisUtil.exists(token)) {
			response.setErrorCode(BaseResponse.invalid_login_code);
			response.setMessage("invalid_login");
			return response;
		}
		User user = userService.findByOpenId(getOpenId(token));
		if(user == null) {
			response.setErrorCode(BaseResponse.invalid_login_code);
			response.setMessage("invalid_login");
			return response;
		}
		Map<String, Object> data = new HashMap<>();
        data.put("userId", user.getId());
        response.setData(data);
		return response;
	}
	
	
	
	//设置用户基本信息
	@GetMapping("setupUserInfo")
	@ResponseBody
	public Object setUpUserInfo(String token, String mpGender, String constellation, String age) {
		
		BaseResponse response = new BaseResponse();
		User user = userService.findByOpenId(getOpenId(token));
		user.setMpGender(Integer.valueOf(mpGender));
		user.setAge(Integer.valueOf(age));
		userService.save(user);
		
		return response;
	}
	
	
	
	public String getSessionKey(String token) {
		String tokenValue = redisUtil.get(token);
		if(tokenValue.startsWith("\"")) {
			return tokenValue.substring(1, tokenValue.length()-1).split(":")[0];
		}
		return tokenValue.split(":")[0];
	}
	
	public String getOpenId(String token) {
		String tokenValue = redisUtil.get(token);
		if(tokenValue.startsWith("\"")) {
			return tokenValue.substring(1, tokenValue.length()-1).split(":")[1];
		}
		return tokenValue.split(":")[1];
	}
	
}
