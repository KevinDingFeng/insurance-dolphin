package com.shenghesun.controller.testController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口是否可用 
 * @author kevin
 *
 */
@RestController
public class TestController {

	@RequestMapping("/hello")
	public void testIndex() {
		System.out.println("访问成功");
	}
	
	
}
