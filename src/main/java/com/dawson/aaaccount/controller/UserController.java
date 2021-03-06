package com.dawson.aaaccount.controller;
 
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.util.TextUtils; 
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.User;
import com.dawson.aaaccount.service.UserService;
import com.dawson.aaaccount.utils.CommonUtils;

@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;

	@RequestMapping("/login")
	public OperateResult<User> login(@RequestBody Map<String, String> author) {
		try {
			User user = new User();
			user.setOpenid(author.get("openid"));
			user.setToken(author.get("access_token"));
			user.setAuthData(author.toString());
			return userService.login(user);
		} catch (Exception e) {
			return CommonUtils.<User>getParamError();
		}
	}

	@RequestMapping("/update")
	public OperateResult<Object> update(@RequestBody User user) {
		if (user == null || TextUtils.isEmpty(user.getId()))
			return CommonUtils.<Object>getParamError();
		else
			return userService.updateInfo(user);
	}

	@RequestMapping("/updateLoginInfo")
	public OperateResult<Object> updateLoginInfo(@RequestBody User user) {
		if (user  == null)
			return CommonUtils.<Object>getParamError();
		else
			return userService.updateLoginInfo(user);
	}
}
