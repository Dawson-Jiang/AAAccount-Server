package com.dawson.aaaccount.service.user;

import com.dawson.aaaccount.entity.LoginInfo;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.User;

public interface UserService {
	OperateResult<User> login(User user);

	OperateResult<Object> updateLoginInfo(LoginInfo loginInfo);

	OperateResult<Object> updateInfo(User user);

}
