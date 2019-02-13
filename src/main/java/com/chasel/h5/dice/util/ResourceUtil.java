package com.chasel.h5.dice.util;

import com.chasel.h5.dice.constant.I18N;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 国际化中英文
 * 
 * @author chasel
 *
 */
public class ResourceUtil {
	
	private static final String MASSAGE_BASE = "config.messages";

	private static final String LANGUAGE_DEFAULT = "zh-CN";

	private static final String LANGUAGE_TOKEN = "languageToken";

	private static final Map<String, Locale> localeMap = new HashMap<>();

	private static final ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();

	static {
		bundle.setBasename(MASSAGE_BASE);
		localeMap.put(I18N.LANGUAGE_ZH, Locale.SIMPLIFIED_CHINESE);
		localeMap.put(I18N.LANGUAGE_EN, Locale.US);
	}

	public static String getMessage(String code, Object... args) {
		if (args == null)
			args = new Object[] {};
		return bundle.getMessage(code, args, code, getLocale());
	}

	private static Locale getLocale() {
		String language = getLanguageCode();
		Locale locale = localeMap.get(language);
		if (locale == null)
			locale = Locale.getDefault();
		return locale;
	}

	public static String getLanguageCode() {
		String language = getHeader(LANGUAGE_TOKEN);
		if (StringUtils.isEmpty(language)) {
			language = LANGUAGE_DEFAULT;
		}
		return language;
	}

	private static String getHeader(String headerName) {
		String headerValue = "";
		RequestAttributes attr = RequestContextHolder.getRequestAttributes();
		if (attr != null) {
			HttpServletRequest request = ((ServletRequestAttributes) attr).getRequest();
			if (request != null) {
				headerValue = request.getHeader(headerName);
			}
			if (StringUtils.isEmpty(headerValue)) {
				headerValue = request.getParameter(headerName);
			}
		}
		if (headerValue == null) {
			headerValue = "";
		}
		return headerValue;
	}

	public static String getUrl() {
		String url = "";
		RequestAttributes attr = RequestContextHolder.getRequestAttributes();
		if (attr != null) {
			HttpServletRequest request = ((ServletRequestAttributes) attr).getRequest();
			if (request != null) {
				url = request.getLocalName() + ":" + request.getLocalPort();
			}
		}
		return url;
	}
	
	protected static String getCookie() {
		String cookieValue = "";
		RequestAttributes attr = RequestContextHolder.getRequestAttributes();
		if (attr != null) {
			HttpServletRequest request = ((ServletRequestAttributes) attr).getRequest();
			if (request != null) {
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if (cookie.equals(LANGUAGE_TOKEN)) {
							cookieValue = cookie.getValue();
						}
					}
				}
			}
		}
		return cookieValue;
	}

}
