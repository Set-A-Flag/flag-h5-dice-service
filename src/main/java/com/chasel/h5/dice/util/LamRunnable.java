package com.chasel.h5.dice.util;

import com.chasel.h5.dice.exception.ServiceException;

/**
 * Lambda表达式，没有返回值
 * 
 * @author chasel
 *
 */
public interface LamRunnable {

	void run() throws ServiceException;

}
