package com.test.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.service.ITestService;

@Controller
public class TestController {
	
	@Autowired
	private ITestService testService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String onLoad(ModelMap model) {
		return "home";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchDetails(HttpServletRequest request,HttpServletResponse respons,ModelMap model) throws Exception {
		System.out.println(request.getParameter("searchVal"));
		List<Map<String,String>> details  = testService.getDetails(request.getParameter("searchVal"));
		model.addAttribute("list", details);
		System.out.println(details);
		return "home";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String removeCity(HttpServletRequest request,HttpServletResponse respons,ModelMap model) throws Exception {
		//remove city logic here
		return "home";
	}

	public ITestService getTestService() {
		return testService;
	}

	public void setTestService(ITestService testService) {
		this.testService = testService;
	}
}
