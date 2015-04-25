package com.company.kaliper.dao;

import com.company.kaliper.exception.EntityPersistException;
import com.company.kaliper.model.Voucher;

public interface VoucherDao {
	void createVoucher(Voucher voucher) throws EntityPersistException;
}
