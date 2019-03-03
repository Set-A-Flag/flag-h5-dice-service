package com.chasel.h5.dice.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chasel.h5.dice.constant.WechatConstants;
import com.chasel.h5.dice.dao.WechatUserDao;
import com.chasel.h5.dice.exception.ServiceException;
import com.chasel.h5.dice.service.IWechatAuthService;
import com.chasel.h5.dice.util.HttpUtils;
import com.chasel.h5.dice.util.WechatUtils;
import com.chasel.h5.dice.vo.WechatUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class WechatAuthServiceImpl implements IWechatAuthService {
    //日志记录
    private static final Logger logger = LoggerFactory.getLogger(WechatAuthServiceImpl.class);

    @Autowired
    private WechatUserDao wechatUserDao;

    @Override
    public void getUserAuthorization() {
        //获取state参数
        String state = HttpUtils.getHttpServletRequest().getParameter("state");

        try {
            //用户授权url
            String authorizationUrl = WechatUtils.getAuthorizationUrl(WechatConstants.SCOPE_TYPE_USER_INFO, state);

            //重定向，跳转到重定向地址
            HttpUtils.getHttpServletResponse().sendRedirect(authorizationUrl);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void auth() {
        HttpServletRequest request = HttpUtils.getHttpServletRequest();
        HttpServletResponse response = HttpUtils.getHttpServletResponse();

        logger.info(request.getRequestURI());

        //获取code
        String code = request.getParameter("code");

        //获取state
        String state = request.getParameter("state");

        //获取accessToken以及openid远程url
        String tokenUrl = WechatUtils.getAccessTokenUrl(code);

        try {
            //调用远程接口获取accessToken以及openid远程响应结果
            String tokenResultString = HttpUtils.doGetString(tokenUrl);

            //accessToken以及openid json对象
            JSONObject accessTokenJsonObj = JSONObject.parseObject(tokenResultString);

            //accessToken
            String accessToken = accessTokenJsonObj.getString("access_token");

            //openId
            String openId = accessTokenJsonObj.getString("openid");


            if (StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(openId)) {
                logger.error(JSON.toJSONString(accessTokenJsonObj));
                response.sendRedirect(state);
                return;
            }

            request.getSession().setAttribute("openId",openId);

            //获取用户信息远程url
            String userInfoUrl = WechatUtils.getUserInfoUrl(accessToken, openId);

            //调用远程接口获取用户信息
            String userInfoResultStr = HttpUtils.doGetString(userInfoUrl);
            wechatUserDao.update(JSONObject.toJavaObject(JSONObject.parseObject(userInfoResultStr),WechatUser.class));

            //用户信息json对象
            JSONObject userInfoJsonObj = JSONObject.parseObject(userInfoResultStr);

            //存储用户信息
            request.getSession().setAttribute("user", userInfoJsonObj);


            Cookie openIdCookie = new Cookie("openId", openId);
            openIdCookie.setPath("/");
            openIdCookie.setMaxAge(-1);
            response.addCookie(openIdCookie);
            response.sendRedirect(state);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public WechatUser getUserInfo() throws ServiceException {
        String openId= (String) HttpUtils.getHttpServletRequest().getSession().getAttribute("openId");

        if (openId == null) {
            logger.error("session不存在openId");

            for (Cookie cookie : HttpUtils.getHttpServletRequest().getCookies()) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                HttpUtils.getHttpServletResponse().addCookie(cookie);
            }

            throw new ServiceException("401", "用户信息不存在，请重新登录");
        }else{
            return wechatUserDao.queryByOpenid(openId);
        }
    }
}
