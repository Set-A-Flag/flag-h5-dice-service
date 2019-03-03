package com.chasel.h5.dice.util;

import com.chasel.h5.dice.constant.WechatConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 微信授权工具类
 */
public class WechatUtils {
    //日志记录
    private static final Logger logger = LoggerFactory.getLogger(WechatUtils.class);

    /**
     * 获取微信网页授权url
     *
     * @param scopeType
     * @param state
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getAuthorizationUrl(String scopeType, String state) throws UnsupportedEncodingException {
        return "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WechatConstants.getAPPID() +
                "&redirect_uri=" + URLEncoder.encode(WechatConstants.getAuthUrl(), "UTF-8") +
                "&response_type=code&scope=" + scopeType + "&state=" + state + "#wechat_redirect";
    }

    public static String getAccessTokenUrl(String code) {
        return "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WechatConstants.getAPPID() +
                "&secret=" + WechatConstants.getSECRET() +
                "&code=" + code +
                "&grant_type=authorization_code";
    }

    public static String getUserInfoUrl(String accessToken, String openId) {
        return "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken +
                "&openid=" + openId +
                "&lang=zh_CN";
    }
}
