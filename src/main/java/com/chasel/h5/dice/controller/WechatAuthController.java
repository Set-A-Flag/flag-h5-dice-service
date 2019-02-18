package com.chasel.h5.dice.controller;

import com.chasel.h5.dice.constant.BaseController;
import com.chasel.h5.dice.service.WechatAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.chasel.h5.dice.constant.Constants.*;

@Controller
@RequestMapping("/wechatAuth")
public class WechatAuthController extends BaseController {
    @Autowired
    private WechatAuthService wechatAuthService;

    @GetMapping("/login")
    public void login(final HttpServletResponse response) {
        wechatAuthService.getUserAuthorization(response);
    }

    @GetMapping("/userInfo")
    public void getUserInfo(final HttpServletRequest request,final HttpServletResponse response){
        wechatAuthService.getUserInfo(request,response);
    }
}
