package com.chasel.h5.dice.service;

import com.chasel.h5.dice.exception.ServiceException;

public interface IDemoService {

    String demo();

    void demo2();

    String demo3(String name) throws ServiceException;
}