package com.chasel.h5.dice.service;

import com.chasel.h5.dice.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信授权-service
 */
public interface WechatAuthService {

    /**
     * 用户同意授权，获取code
     *
     * @param response HttpServletResponse
     */
    public abstract void getUserAuthorization(HttpServletResponse response);

    public abstract void getUserInfo(HttpServletRequest request, HttpServletResponse response);

}
