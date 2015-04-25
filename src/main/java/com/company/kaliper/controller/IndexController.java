package com.company.kaliper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
	@RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
	@ResponseBody
	public String index(){
		return "Hello, its working perfectly";
	}
	
}
