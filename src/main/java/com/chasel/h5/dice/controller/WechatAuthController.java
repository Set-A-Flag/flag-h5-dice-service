package com.chasel.h5.dice.controller;

import com.chasel.h5.dice.constant.Constants;
import com.chasel.h5.dice.service.IWechatAuthService;
import com.chasel.h5.dice.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/wechatAuth")
public class WechatAuthController extends BaseController {
    @Autowired
    private IWechatAuthService IWechatAuthService;

    @GetMapping("/login")
    public void login() {
        IWechatAuthService.getUserAuthorization();
    }

    @GetMapping("/auth")
    public void auth() {
        IWechatAuthService.auth();
    }

    @GetMapping("getUserInfo")
    @ResponseBody
    public ResponseResult getUserInfo(@RequestParam String openId){
        return value(()-> IWechatAuthService.getUserInfo(openId),Constants.QUERY_SUCCESS,Constants.QUERY_FAIL);
    }
}
