package com.chasel.h5.dice.controller;

import static com.chasel.h5.dice.constant.Constants.*;
import com.chasel.h5.dice.constant.BaseController;
import com.chasel.h5.dice.service.DemoService;
import com.chasel.h5.dice.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Api(value = "Demo", description = "Demo")
@RestController
@RequestMapping("/demo")
public class DemoController extends BaseController {

    @Autowired
    DemoService demoService;

    @ApiOperation("demo")
    @GetMapping("/demo")
    public ResponseResult demo (){
        return value(demoService::demo,QUERY_SUCCESS, QUERY_FAIL);
    }

    @ApiOperation("demo2")
    @GetMapping("/demo2")
    public ResponseResult demo2 (){ return process(demoService::demo2,QUERY_SUCCESS, QUERY_FAIL);}

    @ApiOperation("demo3")
    @GetMapping("/demo3")
    public ResponseResult demo2 (@RequestParam(required = false) String name){
        return value(() -> demoService.demo3(name), QUERY_SUCCESS, QUERY_FAIL);
    }
}
