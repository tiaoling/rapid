package com.xiaojiuwo.support.enums;

public enum VerifyOperatorType {
	
	SINGLE("单个操作"),BATCH("批量操作");
	
	
	public String description;
	
	VerifyOperatorType(String description){
		this.description = description;
	}
}
