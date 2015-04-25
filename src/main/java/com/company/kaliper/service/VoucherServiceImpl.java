package com.company.kaliper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("VoucherService")
public class VoucherServiceImpl implements VoucherService{
	
	@Autowired
	@Qualifier("VoucherServiceImplTransactional")
	private VoucherService voucherService;
	
	@Override
	public void createVoucher(){
		voucherService.createVoucher();
	}
	
	
}
