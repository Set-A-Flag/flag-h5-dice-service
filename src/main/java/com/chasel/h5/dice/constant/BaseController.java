package com.chasel.h5.dice.constant;

import com.chasel.h5.dice.exception.ServiceException;
import com.chasel.h5.dice.util.I18NSupport;
import com.chasel.h5.dice.util.LamCallable;
import com.chasel.h5.dice.util.LamRunnable;
import com.chasel.h5.dice.util.ResourceUtil;
import com.chasel.h5.dice.vo.ResponseResult;
import com.chasel.h5.dice.vo.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础Controller
 * 
 * @author chasel
 *
 */
public abstract class BaseController extends I18NSupport {

	private Logger log = LoggerFactory.getLogger(BaseController.class);

	/**
	 * 无返回值
	 * 
	 * @param run
	 * @param successMsg
	 * @param failMsg
	 * @return
	 */
	protected ResponseResult process(LamRunnable run, String successMsg, String failMsg) {
		String responseMsg = "";
		try {
			run.run();
			return new ResponseResult(ResponseStatus.SUCCESS, getMessage(successMsg));

		} catch (IllegalArgumentException e) {
			log.error("process has an error:" , e.getMessage());
			responseMsg = e.getMessage();

		} catch (ServiceException e) {
			log.error("process has an error :" , e.getErrMsg());
			responseMsg = (String) e.getErrMsg();

		} catch (Exception e) {
			log.error("process has an error :" , e.getMessage());
			responseMsg = failMsg;
		}
		return new ResponseResult(ResponseStatus.FAIL, getMessage(responseMsg));
	}

	/**
	 * 有返回值
	 * 
	 * @param run
	 * @param successMsg
	 * @param failMsg
	 * @return
	 */
	protected ResponseResult value(LamCallable run, String successMsg, String failMsg) {
		String responseMsg = "";
		try {
			Object obj = run.run();
			return new ResponseResult(ResponseStatus.SUCCESS, getMessage(successMsg), obj);
		} catch (IllegalArgumentException e) {
			log.error("value has an error :" , e.getMessage());
			responseMsg = e.getMessage();

		} catch (ServiceException e) {
			responseMsg = (String) e.getErrMsg();
			log.error("value has an error :" , e.getErrMsg());
		} catch (Exception e) {
			log.error("value has an error :" , e.getMessage());
			responseMsg = failMsg;
		}
		return new ResponseResult(ResponseStatus.FAIL, getMessage(responseMsg));
	}
}
