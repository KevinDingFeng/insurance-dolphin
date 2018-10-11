package com.shenghesun.controller.manager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ManagerController {

	@RequestMapping(value = "/manager")
	public String getPrePayId(HttpServletRequest request) throws Exception {
		System.out.println("进入管理界面");
		return "index";
	}

	@GetMapping("te")
	public ModelAndView view(Model model) {
		model.addAttribute("user", "丽丽");
		model.addAttribute("title", "查看用户");
		return new ModelAndView("views/index", "userModel", model);
	}
}
