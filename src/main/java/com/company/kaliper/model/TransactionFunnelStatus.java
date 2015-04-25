package com.company.kaliper.model;

import java.util.HashMap;

public enum TransactionFunnelStatus {
	INITIALIZED(1),
	VALIDATED(2),
	PROCESSING(3),
	SUCCESS(4),
	FAILED(5);
	
	private static HashMap<Integer,TransactionFunnelStatus> store = new HashMap<Integer,TransactionFunnelStatus>();
	
	static{
		for(TransactionFunnelStatus en : TransactionFunnelStatus.values()){
			store.put(en.getValue(), en);
		}
	}
	
	private int value;
	
	TransactionFunnelStatus(int key){
		this.value = key;
	}
	
	public int getValue() { 
		return value; 
	}
	
	public static TransactionFunnelStatus getEnumFor(int value){
		return TransactionFunnelStatus.store.get(value);
	}

}
