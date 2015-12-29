package com.xiaojiuwo.support.enums;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EnumConverter implements  Converter<String,VerifyStatus>{

	@Override
	public VerifyStatus convert(String source) {
		
		return VerifyStatus.values()[Integer.parseInt(source)];
	}

}
