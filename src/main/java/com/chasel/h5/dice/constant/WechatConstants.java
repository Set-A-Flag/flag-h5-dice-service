package com.chasel.h5.dice.constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 微信配置-常量
 */
@Configuration
public class WechatConstants {
    //公众号appId
    private static String APPID;

    //公众号secret
    private static String SECRET;

    //accessToken远程接口地址
    private static String AUTH_URL;

    //scope类型，能获取到微信用户基本信息
    public static final String SCOPE_TYPE_USER_INFO = "snsapi_userinfo";

    //scope类型，只能获取到微信用户openid
    public static final String SCOPE_TYPE_BASE = "snsapi_base";

    @Autowired(required = true)
    private void setAPPID(@Value("${wechat.appId}") String APPID) {
        WechatConstants.APPID = APPID;
    }

    @Autowired(required = true)
    private void setSECRET(@Value("${wechat.secret}") String SECRET) {
        WechatConstants.SECRET = SECRET;
    }

    @Autowired
    private void setAuthUrl(@Value("${wechat.authUrl}") String authUrl) {
        WechatConstants.AUTH_URL = authUrl;
    }

    public static String getAPPID() {
        return APPID;
    }

    public static String getSECRET() {
        return SECRET;
    }

    public static String getAuthUrl() {
        return AUTH_URL;
    }
}
