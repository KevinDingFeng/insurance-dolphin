package com.shenghesun.controller.manager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagerController {
	
	 @RequestMapping(value = "/manager")
	public String getPrePayId(HttpServletRequest request) throws Exception {
		 System.out.println("进入管理界面");
	return "index";
	}
}
