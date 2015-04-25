package com.company.kaliper.service;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.kaliper.dao.VoucherDao;
import com.company.kaliper.exception.EntityPersistException;
import com.company.kaliper.model.Voucher;
import com.company.kaliper.model.VoucherStatus;

@Service("VoucherServiceImplTransactional")
@Transactional
public class VoucherServiceImplTransactional implements VoucherService{
	
	@Autowired
	private VoucherDao voucherDao;
	
	@Override
	public void createVoucher() {
		Voucher voucher = new Voucher();
		voucher.setAvailableCredit(1000);
		voucher.setInitialCredit(1000);
		voucher.setVoucherCode("SDJAS-SDFSDF-SDFSDF-EWRTE");
		voucher.setCreatedBy("Vyshakh");
		
		voucher.setValidFrom(Calendar.getInstance().getTime());
		
		Calendar c = Calendar.getInstance();
		c.setTime(voucher.getValidFrom());
		c.add(Calendar.YEAR, 2);
		voucher.setExpiryDate(c.getTime());
		
		voucher.setStatus(VoucherStatus.PENDING);
		try{
			voucherDao.createVoucher(voucher);
		}catch(EntityPersistException ex){
			ex.printStackTrace();
		}
	}

}
