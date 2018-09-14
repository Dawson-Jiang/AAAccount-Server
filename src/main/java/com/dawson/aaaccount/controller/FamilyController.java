package com.dawson.aaaccount.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.util.TextUtils;
import org.hibernate.annotations.Any;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawson.aaaccount.CommonUtils;
import com.dawson.aaaccount.entity.Daybook;
import com.dawson.aaaccount.entity.ErrorCode;
import com.dawson.aaaccount.entity.Family;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.service.family.FamilyService;

@RestController
@RequestMapping("/family")
public class FamilyController {

	@Resource
	private FamilyService familyService;

	@RequestMapping("/save")
	public OperateResult<String> save(@RequestBody Family family) {
	 
			if (family==null)
				return CommonUtils.<String>getParamError();
			else
				return familyService.save(family);
	
	}
	
	@RequestMapping("/get")
	public OperateResult<Family> get(@RequestBody String id) {

			if (TextUtils.isEmpty(id))
				return CommonUtils.<Family>getParamError();
			else
				return familyService.get(id);
	}

	@RequestMapping("/get_my_family")
	public OperateResult<List<Family>> getMyFamily(@RequestBody String userid) {
	 
			if (TextUtils.isEmpty(userid))
				return CommonUtils.<List<Family>>getParamError();
			else
				return familyService.getMyFamily(userid);
		 
	}
	@RequestMapping("/join")
	public OperateResult<Object> join(@RequestBody Map<String, String>body) {
		String fid = null; 
		String uid = null; 
		try { 
			fid=  body.get("fid");
			uid=  body.get("uid");
			 
			if(TextUtils.isEmpty(fid)||TextUtils.isEmpty(uid) )	return new OperateResult<Object>(ErrorCode.PARAM_ERROR,"参数错误");
		} catch (Exception e) {
			return new OperateResult<Object>(ErrorCode.PARAM_ERROR,"参数错误");
		}

		return familyService.join(fid, uid);
	}
	
	
	@RequestMapping("/dis_join")
	public OperateResult<Object> disJoin(@RequestBody Map<String, String>body) {
		String fid = null; 
		String uid = null; 
		try { 
			fid=  body.get("fid");
			uid=  body.get("uid");
			 
			if(TextUtils.isEmpty(fid)||TextUtils.isEmpty(uid) )	return new OperateResult<Object>(ErrorCode.PARAM_ERROR,"参数错误");
		} catch (Exception e) {
			return new OperateResult<Object>(ErrorCode.PARAM_ERROR,"参数错误");
		}

		return familyService.disJoin(fid, uid);
	} 
}
