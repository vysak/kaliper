package com.company.kaliper.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.company.kaliper.exception.EntityPersistException;
import com.company.kaliper.model.Voucher;

@Repository("VoucherDao")
public class VoucherDaoImpl implements VoucherDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void createVoucher(Voucher voucher) throws EntityPersistException {
		try{
			em.persist(voucher);
		}catch(Exception e){
			throw new EntityPersistException("Could not able to persist the Voucher[" + voucher.toString()+"]", e);
		}
	}

}
