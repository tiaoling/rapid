package com.xiaojiuwo.support.enums;

public enum MatchStatus{
	
	MATCHNO("未匹配"),MATCHYES("匹配");
	
	public String description;
	
	MatchStatus(String description){
		this.description = description;
	}
}
