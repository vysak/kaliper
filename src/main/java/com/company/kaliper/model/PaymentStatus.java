package com.company.kaliper.model;

import java.util.HashMap;

public enum PaymentStatus {
	INITIALIZED(1),
	APPROVED(2),
	SUCCESS(3),
	FAILED(4),
	CANCELED(5);
	
	private static HashMap<Integer,PaymentStatus> store = new HashMap<Integer,PaymentStatus>();
	
	static{
		for(PaymentStatus en : PaymentStatus.values()){
			store.put(en.getValue(), en);
		}
	}
	
	private int value;
	
	PaymentStatus(int key){
		this.value = key;
	}
	
	public int getValue() { 
		return value; 
	}
	
	public static PaymentStatus getEnumFor(int value){
		return PaymentStatus.store.get(value);
	}

}
