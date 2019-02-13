package com.chasel.h5.dice.constant;

import java.io.Serializable;

public class Constants implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//项目结构变动需要改动
	public static final String EXECUTION="execution(public * com.chasel.h5.dice.controller.*.*(..))";
	public static final String BASEPACKGE="com.chasel.h5.dice";

	public static final String ADD_SUCCESS = "add.success";

	public static final String ADD_FAIL = "add.fail";

	public static final String DEL_SUCCESS = "del.success";

	public static final String DEL_FAIL = "del.fail";

	public static final String UPDATE_SUCCESS = "update.success";

	public static final String UPDATE_FAIL = "update.fail";

	public static final String QUERY_SUCCESS = "query.success";

	public static final String QUERY_FAIL = "query.fail";

	public static final String PARAM_NULL = "param.null";

}
