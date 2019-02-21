package com.chasel.h5.dice.service;

import com.alibaba.fastjson.JSONObject;
import com.chasel.h5.dice.exception.ServiceException;
import com.chasel.h5.dice.vo.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信授权-service
 */
public interface WechatAuthService {

    /**
     * 用户同意授权，回调页面获取code
     */
    public abstract void getUserAuthorization();

    /**
     * 获取openid和accessToken，用户信息
     */
    public abstract void auth();

    /**
     * 获取用户信息
     *
     * @param openId
     * @return
     */
    public abstract JSONObject getUserInfo(String openId);

}
