package com.dawson.aaaccount.utils;

import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.User;

public class CommonUtils {
	public static <T> OperateResult<T> getParamError() {
		return new OperateResult<T>(0, "参数格式错误!");
	}

	public static boolean userEquals(User user1, User user2) {
		if (user1 == null && user2 == null)
			return true;
		if (user1 != null && user2 != null) {
			return stringEquals(user1.getId(), user2.getId());
		} else
			return false;
	}

	public static boolean stringEquals(String str1, String str2) {
		if (str1 == null && str2 == null)
			return true;
		if (str1 != null && str2 != null) {
			return str1.equals(str2);
		} else
			return false;
	}
}
