package com.dawson.aaaccount;

import com.dawson.aaaccount.entity.OperateResult;

public class CommonUtils {
public static <T> OperateResult<T> getParamError(){
		return new OperateResult<T>(0, "参数格式错误!");
	}
}
