package com.chasel.h5.dice.controller;

import com.chasel.h5.dice.constant.Constants;
import com.chasel.h5.dice.service.WechatAuthService;
import com.chasel.h5.dice.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/wechatAuth")
public class WechatAuthController extends BaseController {
    @Autowired
    private WechatAuthService wechatAuthService;

    @GetMapping("/login")
    public void login() {
        wechatAuthService.getUserAuthorization();
    }

    @GetMapping("/auth")
    public void auth() {
        wechatAuthService.auth();
    }

    @GetMapping("getUserInfo")
    @ResponseBody
    public ResponseResult getUserInfo(@RequestParam String openId){
        return value(()->wechatAuthService.getUserInfo(openId),Constants.QUERY_SUCCESS,Constants.QUERY_FAIL);
    }
}
