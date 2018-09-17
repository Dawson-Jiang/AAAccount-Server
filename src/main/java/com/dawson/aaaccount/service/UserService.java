package com.dawson.aaaccount.service;
 
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.User;

public interface UserService {
	OperateResult<User> login(User user);

	OperateResult<Object> updateLoginInfo(User user);

	OperateResult<Object> updateInfo(User user);

}
