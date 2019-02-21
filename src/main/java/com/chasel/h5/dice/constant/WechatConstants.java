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
    public static String APPID;

    //公众号secret
    public static String SECRET;

    @Autowired(required = true)
    public void setAPPID(@Value("${wechat.appId}") String APPID) {
        WechatConstants.APPID = APPID;
    }

    @Autowired(required = true)
    public void setSECRET(@Value("${wechat.secret}") String SECRET) {
        WechatConstants.SECRET = SECRET;
    }
}
