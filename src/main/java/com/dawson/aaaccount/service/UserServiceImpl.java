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
		User tUser = userRepository.findById(user.getId()).get();
		if (user.getLoginInfo() == null) {
			tUser.setLastLoginTime(new Date());
			tUser.setUpdateTime(new Date());
		} else {
			LoginInfo tloginInfo = user.getLoginInfo();
			if (tUser.getLoginInfo() != null) {
				tloginInfo.setId(tUser.getLoginInfo().getId());
				tloginInfo = loginInfoRepository.save(tloginInfo);
			} else {
				tloginInfo=	loginInfoRepository.save(tloginInfo);
				tUser.setLoginInfo(tloginInfo);
			}
		}
		boolean res = userRepository.save(tUser) != null;

		if (res)
			return new OperateResult<Object>("");
		else
			return new OperateResult<Object>(ErrorCode.FAIL, "");
	}

	@Override
	public OperateResult<Object> updateInfo(User user) {
		User tUser = userRepository.findById(user.getId()).get();
		tUser.setName(user.getName());
		tUser.setEmail(user.getEmail());
		tUser.setHeadPic(user.getHeadPic());
		tUser.setUpdateTime(new Date());
		int res = userRepository.save(tUser) == null ? 0 : 1;
		if (res > 0)
			return new OperateResult<Object>("");
		else
			return new OperateResult<Object>(ErrorCode.FAIL, "");
	}

}
