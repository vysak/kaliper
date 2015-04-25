package com.company.kaliper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.kaliper.service.VoucherService;

@Controller
@RequestMapping(value = "/voucher")
public class VoucherController {
	
	@Autowired
	@Qualifier("VoucherService")
	private VoucherService voucherService;
	
	
	@RequestMapping(value = {"/create"}, method = RequestMethod.GET)
	@ResponseBody
	public String createVoucher(){
		voucherService.createVoucher();
		return "Successfully created voucher";
	}

}
