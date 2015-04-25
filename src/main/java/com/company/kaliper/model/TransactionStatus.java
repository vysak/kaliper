package com.company.kaliper.model;

import java.util.HashMap;

public enum TransactionStatus {
	INITIALIZED(1),
	SUCCESS(2),
	FAILED(3);
	
	private static HashMap<Integer,TransactionStatus> store = new HashMap<Integer,TransactionStatus>();
	
	static{
		for(TransactionStatus en : TransactionStatus.values()){
			store.put(en.getValue(), en);
		}
	}
	
	private int value;
	
	TransactionStatus(int key){
		this.value = key;
	}
	
	public int getValue() { 
		return value; 
	}
	
	public static TransactionStatus getEnumFor(int value){
		return TransactionStatus.store.get(value);
	}

}
