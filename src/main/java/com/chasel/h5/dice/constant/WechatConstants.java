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

    //获取accessToken远程接口地址
    private static String authUrl;

    @Autowired(required = true)
    public void setAPPID(@Value("${wechat.appId}") String APPID) {
        WechatConstants.APPID = APPID;
    }

    @Autowired(required = true)
    public void setSECRET(@Value("${wechat.secret}") String SECRET) {
        WechatConstants.SECRET = SECRET;
    }

    @Autowired
    public void setAuthUrl(@Value("${wechat.authUrl}") String authUrl) {
        WechatConstants.authUrl = authUrl;
    }

    public static String getAPPID() {
        return APPID;
    }

    public static String getSECRET() {
        return SECRET;
    }

    public static String getAuthUrl() {
        return authUrl;
    }
}
