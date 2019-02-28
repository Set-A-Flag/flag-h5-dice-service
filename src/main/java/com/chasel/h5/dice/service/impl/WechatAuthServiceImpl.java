package com.chasel.h5.dice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chasel.h5.dice.constant.WechatConstants;
import com.chasel.h5.dice.exception.ServiceException;
import com.chasel.h5.dice.service.IWechatAuthService;
import com.chasel.h5.dice.util.HttpUtils;
import com.chasel.h5.dice.util.WechatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public class WechatAuthServiceImpl implements IWechatAuthService {
    //日志记录
    private static final Logger logger = LoggerFactory.getLogger(WechatAuthServiceImpl.class);

    @Override
    public void getUserAuthorization() {
        //获取state参数
        String state = HttpUtils.getHttpServletRequest().getParameter("state");

        //scope类型，snsapi_userinfo|snsapi_base
        String scopeType = "snsapi_userinfo";

        try {
            //用户授权url
            String authorizationUrl = WechatUtils.getAuthorizationUrl(WechatConstants.getAuthUrl(), scopeType, state);

            //重定向，跳转到重定向地址
            HttpUtils.getHttpServletResponse().sendRedirect(authorizationUrl);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void auth() {
        HttpServletRequest request = HttpUtils.getHttpServletRequest();
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

            //获取用户信息远程url
            String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken +
                    "&openid=" + openId +
                    "&lang=zh_CN";

            //调用远程接口获取用户信息
            String userInfoResultStr = HttpUtils.doGetString(userInfoUrl);

            //用户信息json对象
            JSONObject userInfoJsonObj = JSONObject.parseObject(userInfoResultStr);

            //TO-DO，存储用户信息，暂存到session中
            request.getSession().setAttribute(openId, userInfoJsonObj);

            logger.info(userInfoJsonObj.toJSONString());
            HttpServletResponse response = HttpUtils.getHttpServletResponse();

            Cookie openIdCookie = new Cookie("openId", openId);
            openIdCookie.setPath("/");
            response.addCookie(openIdCookie);

            response.sendRedirect(state);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }


    }

    @Override
    public JSONObject getUserInfo(String openId) throws ServiceException {
        //TO-DO，根据openId获取用户信息，暂时从session中获取，未持久化到数据库中，用户信息会丢失。
        HttpSession session = HttpUtils.getHttpServletRequest().getSession();
        Object userInfo = session.getAttribute(openId);

        if (userInfo == null) {
            logger.error("session不存在用户信息");
            for (Cookie cookie : HttpUtils.getHttpServletRequest().getCookies()) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                HttpUtils.getHttpServletResponse().addCookie(cookie);
                throw new ServiceException("401", "用户信息不存在，请重新登录");
            }
        }

        return (JSONObject) userInfo;
    }
}
