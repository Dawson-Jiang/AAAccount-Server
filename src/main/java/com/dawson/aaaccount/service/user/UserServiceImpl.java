package com.dawson.aaaccount.service.user;

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
	private UserRepository userDao;

	@Resource
	private LoginInfoRepository loginDao;

	@Override
	public OperateResult<User> login(User user) {
		User tUser = userDao.findByOpenid(user.getOpenid());

		if (tUser == null) {
			tUser = new User();
			tUser.setOpenid(user.getOpenid());
		}
		tUser.setToken(user.getToken());
		tUser.setLastLoginTime(new Date());
		tUser.setAuthData(user.getAuthData());
		tUser = userDao.save(tUser);

		if (tUser != null)
			return new OperateResult<User>(tUser);
		else
			return new OperateResult<User>(ErrorCode.FAIL, "");
	}

	@Override
	public OperateResult<Object> updateLoginInfo(LoginInfo loginInfo) {
		LoginInfo tloginInfo = null;
		if (loginInfo.getVersion() != null && !loginInfo.getVersion().isEmpty()) {
			tloginInfo = loginDao.findByUser(loginInfo.getUser());
			if (tloginInfo == null) {
				tloginInfo = loginDao.save(loginInfo);
			} else {
				loginInfo.setId(tloginInfo.getId());
				loginInfo.setUpdateTime(new Date());
				tloginInfo = loginDao.save(loginInfo);
			}
		} else
			tloginInfo = loginInfo;
		boolean res = tloginInfo != null;
		if (res) {
			User user = new User();
			user.setId(tloginInfo.getUser().getId());
			user.setLastLoginTime(new Date());
			user.setUpdateTime(new Date());
			res = userDao.save(user) != null;
		}
		if (res)
			return new OperateResult<Object>("");
		else
			return new OperateResult<Object>(ErrorCode.FAIL, "");
	}

	@Override
	public OperateResult<Object> updateInfo(User user) {
		user.setUpdateTime(new Date());
		int res = userDao.save(user) == null ? 0 : 1;
		if (res > 0)
			return new OperateResult<Object>("");
		else
			return new OperateResult<Object>(ErrorCode.FAIL, "");
	}

}
