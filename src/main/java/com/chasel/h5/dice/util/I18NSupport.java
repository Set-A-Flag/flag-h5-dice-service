package com.chasel.h5.dice.util;

public class I18NSupport {

	public String getMessage(String code, Object... args) {
		return ResourceUtil.getMessage(code, args);
	}

	public String getUrl() {
		return ResourceUtil.getUrl();
	}

	public String getLanguageCode() {
		return ResourceUtil.getLanguageCode();
	}
}
