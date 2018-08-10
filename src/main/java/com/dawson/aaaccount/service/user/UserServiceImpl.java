package com.dawson.aaaccount.service.user;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dawson.aaaccount.domain.DaybookMapper;
import com.dawson.aaaccount.domain.LoginInfoMapper;
import com.dawson.aaaccount.domain.UserMapper;
import com.dawson.aaaccount.entity.ErrorCode;
import com.dawson.aaaccount.entity.LoginInfo;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userDao;

	@Resource
	private LoginInfoMapper loginDao;

	@Override
	public OperateResult<User> login(User user) {
		User tUser = userDao.selectByOpenid(user.getOpenid());
		int res = 0;
		if (tUser == null) {
			tUser = new User();
			tUser.setOpenid(user.getOpenid());
			tUser.setToken(user.getToken());
			tUser.setLastLoginTime(new Date());
			tUser.setAuthData(user.getAuthData());
			res = userDao.insertSelective(tUser);
		} else {
			tUser.setToken(user.getToken());
			tUser.setLastLoginTime(new Date());
			tUser.setAuthData(user.getAuthData());
			res = userDao.updateByPrimaryKeySelective(tUser);
		}
		if (res > 0)
			return new OperateResult<User>(tUser);
		else
			return new OperateResult<User>(ErrorCode.FAIL, "");
	}

	@Override
	public OperateResult<Object> updateLoginInfo(LoginInfo loginInfo) {
		int res = 0;
		if (loginInfo.getVersion() != null && !loginInfo.getVersion().isEmpty()) {
			LoginInfo tloginInfo = loginDao.selectByUserId(loginInfo.getUserId());
			if (tloginInfo == null) {
				res = loginDao.insertSelective(loginInfo);
			} else {
				loginInfo.setId(tloginInfo.getId());
				loginInfo.setUpdateTime(new Date());
				res = loginDao.updateByPrimaryKeySelective(loginInfo);
			}

		} else
			res = 1;
		if (res > 0) {
			User user = new User();
			user.setId(loginInfo.getUserId());
			user.setLastLoginTime(new Date());
			user.setUpdateTime(new Date());
			res = userDao.updateByPrimaryKeySelective(user);
		}
		if (res > 0)
			return new OperateResult<Object>("");
		else
			return new OperateResult<Object>(ErrorCode.FAIL, "");
	}

	@Override
	public OperateResult<Object> updateInfo(User user) {
		user.setUpdateTime(new Date());
		int res = userDao.updateByPrimaryKeySelective(user);
		if (res > 0)
			return new OperateResult<Object>("");
		else
			return new OperateResult<Object>(ErrorCode.FAIL, "");
	}

}
