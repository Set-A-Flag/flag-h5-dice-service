package com.chasel.h5.dice.service.impl;

import static com.chasel.h5.dice.constant.Constants.*;
import com.chasel.h5.dice.exception.ServiceException;
import com.chasel.h5.dice.service.DemoService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DemoServiceImpl implements DemoService {


    @Override
    public String demo() {
        System.out.println("这是有返回值的方法。");
        return "这是有返回值的方法。";
    }

    @Override
    public void demo2() {
        System.out.println("这是没有返回值的方法。");
    }

    @Override
    public String demo3(String name) throws ServiceException {
        System.out.println("如果name参数不传，这是一个抛自定义异常的方法。");
        if (StringUtils.isEmpty(name)) throw new ServiceException(PARAM_NULL);
        return "再试一次，请不要传参数name.";
    }
}
