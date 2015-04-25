package com.company.kaliper.model;

import java.util.HashMap;

public enum VoucherStatus {
	PENDING(1),
	ACTIVE(2),
	INACTIVE(3),
	REVOKED(4),
	EXPIRED(5);
	
	private static HashMap<Integer,VoucherStatus> store = new HashMap<Integer,VoucherStatus>();
	
	static{
		for(VoucherStatus en : VoucherStatus.values()){
			store.put(en.getValue(), en);
		}
	}
	
	private int value;
	
	VoucherStatus(int key){
		this.value = key;
	}
	
	public int getValue() { 
		return value; 
	}
	
	public static VoucherStatus getEnumFor(int value){
		return VoucherStatus.store.get(value);
	}
}

