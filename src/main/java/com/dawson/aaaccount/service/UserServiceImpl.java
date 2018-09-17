package com.dawson.aaaccount.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dawson.aaaccount.entity.ErrorCode;
import com.dawson.aaaccount.entity.LoginInfo;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.User;
import com.dawson.aaaccount.repository.LoginInfoRepository;
import com.dawson.aaaccount.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserRepository userRepository;

	@Resource
	private LoginInfoRepository loginInfoRepository;

	@Override
	public OperateResult<User> login(User user) {
		User tUser = userRepository.findByOpenid(user.getOpenid());

		if (tUser == null) {
			tUser = new User();
			tUser.setOpenid(user.getOpenid());
		}
		tUser.setToken(user.getToken());
		tUser.setLastLoginTime(new Date());
		tUser.setAuthData(user.getAuthData());
		tUser = userRepository.save(tUser);

		if (tUser != null)
			return new OperateResult<User>(tUser);
		else
			return new OperateResult<User>(ErrorCode.FAIL, "");
	}

	@Override
	public OperateResult<Object> updateLoginInfo(User user) {
		LoginInfo tloginInfo = null;
		if (user.getLoginInfo() != null) {
			User tUser = userRepository.findById(user.getId()).get();
			if (tUser.getLoginInfo() != null) {
				user.getLoginInfo().setId(tUser.getLoginInfo().getId());
				tloginInfo = loginInfoRepository.save(user.getLoginInfo());
				user.setLoginInfo(null);
			} else {
				tloginInfo = loginInfoRepository.save(user.getLoginInfo());
				user.setLoginInfo(tloginInfo);
			}
		}

		user.setLastLoginTime(new Date());
		user.setUpdateTime(new Date());
		boolean res = userRepository.save(user) != null;

		if (res)
			return new OperateResult<Object>("");
		else
			return new OperateResult<Object>(ErrorCode.FAIL, "");
	}

	@Override
	public OperateResult<Object> updateInfo(User user) {
		user.setUpdateTime(new Date());
		int res = userRepository.save(user) == null ? 0 : 1;
		if (res > 0)
			return new OperateResult<Object>("");
		else
			return new OperateResult<Object>(ErrorCode.FAIL, "");
	}

}