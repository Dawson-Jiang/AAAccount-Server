package com.dawson.aaaccount.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawson.aaaccount.entity.Daybook;
import com.dawson.aaaccount.entity.LoginInfo;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.User;
import com.dawson.aaaccount.service.daybook.DaybookService;
import com.dawson.aaaccount.service.user.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;

	@RequestMapping("/login")
	public OperateResult<User> login(@RequestBody Map<String, String> author) {
		User user = new User();
		user.setOpenid(author.get("openid"));
		user.setToken(author.get("access_token"));
		user.setAuthData(author.toString());
		return userService.login(user);
	}
	
	@RequestMapping("/update")
	public OperateResult<Object> update(@RequestBody User user) {
		return userService.updateInfo(user);
	}
	
	@RequestMapping("/updateLoginInfo")
	public OperateResult<Object> updateLoginInfo(@RequestBody LoginInfo loginInfo) {
		return userService.updateLoginInfo(loginInfo);
	}
}
