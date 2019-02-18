package com.chasel.h5.dice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chasel.h5.dice.service.WechatAuthService;
import com.chasel.h5.dice.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

@Service
public class WechatAuthServiceImpl implements WechatAuthService {
    //日志记录
    private static final Logger logger = LoggerFactory.getLogger(WechatAuthServiceImpl.class);

    //公众号appid
    private static final String APPID = "wx3abc94d28018f67b";

    //公众号secret
    private static final String SECRET = "782e2d1df576cc62db88542bb47da575";

    @Override
    public void getUserAuthorization(HttpServletResponse response) {


        //授权成功后回调地址
        String redirectUrl = "http://o2n3712597.imwork.net/wechatAuth/userInfo";

        try {
            //用户授权url
            String authorizationUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APPID +
                    "&redirect_uri=" + URLEncoder.encode(redirectUrl, "UTF-8") +
                    "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

            //重定向到回调地址
            response.sendRedirect(authorizationUrl);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        //获取code
        String code = request.getParameter("code");

        //获取accessToken以及openid远程url
        String tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID +
                "&secret=" + SECRET +
                "&code=" + code +
                "&grant_type=authorization_code";

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
                    "D&lang=zh_CN";

            //调用远程接口获取用户信息
            String userInfoResultStr=HttpUtils.doGetString(userInfoUrl);

            //用户信息json对象
            JSONObject userInfoJsonObj=JSONObject.parseObject(userInfoResultStr);

            HttpSession session=request.getSession();
            session.setAttribute("userInfo",userInfoJsonObj);
            response.sendRedirect("/");
            logger.info(userInfoResultStr);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }


    }
}
