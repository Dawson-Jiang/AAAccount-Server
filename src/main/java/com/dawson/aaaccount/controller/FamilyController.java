package com.dawson.aaaccount.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.util.TextUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawson.aaaccount.CommonUtils;
import com.dawson.aaaccount.entity.Family;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.service.family.FamilyService;

@RestController
@RequestMapping("/family")
public class FamilyController {

	@Resource
	private FamilyService familyService;

	@RequestMapping("/get_family")
	public OperateResult<Family> getFamily(@RequestBody String id) {
		try {
			if (TextUtils.isEmpty(id))
				return CommonUtils.<Family>getParamError();
			else
				return familyService.getFamily(id);
		} catch (Exception e) {
			return CommonUtils.<Family>getParamError();
		}
	}

	@RequestMapping("/get_user_family")
	public OperateResult<List<Family>> getUserFamily(@RequestBody String userid) {
		try {
			if (TextUtils.isEmpty(userid))
				return CommonUtils.<List<Family>>getParamError();
			else
				return familyService.getFamilyByUserId(userid);
		} catch (Exception e) {
			return CommonUtils.<List<Family>>getParamError();
		}
	}
}
