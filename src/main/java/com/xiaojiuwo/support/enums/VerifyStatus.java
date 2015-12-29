package com.xiaojiuwo.support.enums;

public enum VerifyStatus{
	
	NOVERIFY("未审核"),VERIFYNO("审核不通过"),VERIFYYES("审核通过");
	
	public String description;
	
	VerifyStatus(String description){
		this.description = description;
	}
}