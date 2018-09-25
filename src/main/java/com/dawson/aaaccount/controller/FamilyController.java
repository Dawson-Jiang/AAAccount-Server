package com.dawson.aaaccount.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.util.TextUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawson.aaaccount.CommonUtils;
import com.dawson.aaaccount.entity.ErrorCode;
import com.dawson.aaaccount.entity.Family;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.User;
import com.dawson.aaaccount.service.FamilyService;

@RestController
@RequestMapping("/family")
public class FamilyController {

	@Resource
	private FamilyService fService;

	@RequestMapping("/save")
	public OperateResult<String> save(@RequestBody Family family) {

		if (family == null)
			return CommonUtils.<String>getParamError();
		else
			return fService.save(family);

	}

	@RequestMapping("/get")
	public OperateResult<Family> get(@RequestBody Map<String, String> body) {
		String id = body.get("id");
		if (TextUtils.isEmpty(id))
			return CommonUtils.<Family>getParamError();
		else
			return fService.get(id);
	}

	@RequestMapping("/get_my_family")
	public OperateResult<List<Family>> getMyFamily(@RequestBody Map<String, String> body) {
		String userid = body.get("uid");
		if (TextUtils.isEmpty(userid))
			return CommonUtils.<List<Family>>getParamError();
		else
			return fService.getMyFamily(userid);

	}

	@RequestMapping("/join")
	public OperateResult<String> join(@RequestBody Map<String, String> body) {
		String fid = null;
		String uid = null;
		String uname = null;
		try {
			fid = body.get("fid");
			uid = body.get("uid");
			uname = body.get("uname");
			if (TextUtils.isEmpty(fid))
				return new OperateResult<String>(ErrorCode.PARAM_ERROR, "参数错误");
			if (TextUtils.isEmpty(uid) && TextUtils.isEmpty(uname))
				return new OperateResult<String>(ErrorCode.PARAM_ERROR, "参数错误");
		} catch (Exception e) {
			return new OperateResult<String>(ErrorCode.PARAM_ERROR, "参数错误");
		}
		User user = new User();
		user.setId(uid);
		user.setName(uname);
		return fService.join(fid, user);
	}

	@RequestMapping("/dis_join")
	public OperateResult<Object> disJoin(@RequestBody Map<String, String> body) {
		String fid = null;
		String uid = null;
		try {
			fid = body.get("fid");
			uid = body.get("uid");

			if (TextUtils.isEmpty(fid) || TextUtils.isEmpty(uid))
				return new OperateResult<Object>(ErrorCode.PARAM_ERROR, "参数错误");
		} catch (Exception e) {
			return new OperateResult<Object>(ErrorCode.PARAM_ERROR, "参数错误");
		}
		return fService.disJoin(fid, uid);
	}

	@RequestMapping("/del")
	public OperateResult<Object> del(@RequestBody Map<String, String> body) {
		String id = body.get("id");
		if (TextUtils.isEmpty(id))
			return new OperateResult<Object>(ErrorCode.PARAM_ERROR, "参数错误");
		return fService.del(id);
	}
}
