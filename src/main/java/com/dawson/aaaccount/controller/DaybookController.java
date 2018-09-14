package com.dawson.aaaccount.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.function.Consumer;

import javax.annotation.Resource;

import org.apache.http.util.TextUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.dawson.aaaccount.entity.User;
import com.dawson.aaaccount.entity.Category;
import com.dawson.aaaccount.entity.Daybook;
import com.dawson.aaaccount.entity.ErrorCode;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.service.daybook.DaybookService;

@RestController
@RequestMapping("/daybook")
public class DaybookController {

	@Resource
	private DaybookService daybookService;


	@RequestMapping("/save")
	public OperateResult<String> save(@RequestBody Daybook body) {
		return daybookService.save(body);
	}
	
	@RequestMapping("/get_family_daybook")
	public OperateResult<List<Daybook>> getFamilyDaybook(@RequestBody Map<String, String> body) {
		String fid = null; 
		int page = 0;
		int limit = 10;
		try { 
			fid=  body.get("fid");
			page = Integer.parseInt(body.get("page"));
			limit = Integer.parseInt(body.get("limit"));  
			if(TextUtils.isEmpty(fid)||page<0||limit<0)	return new OperateResult<List<Daybook>>(ErrorCode.PARAM_ERROR,"参数错误");
		} catch (Exception e) {
			return new OperateResult<List<Daybook>>(ErrorCode.PARAM_ERROR,"参数错误");
		}

		return daybookService.getFamilyDaybook(fid, page, limit);
	}
	
	@RequestMapping("/get_my_daybook")
	public OperateResult<List<Daybook>> getMyDaybook(@RequestBody Map<String, String> body) {
		String uid = null;
		int page = 0;
		int limit = 10;
		try { 
		    uid=  body.get("uid");
			page = Integer.parseInt(body.get("page"));
			limit = Integer.parseInt(body.get("limit"));  
			if(TextUtils.isEmpty(uid)||page<0||limit<0)	return new OperateResult<List<Daybook>>(ErrorCode.PARAM_ERROR,"参数错误");
		} catch (Exception e) {
			return new OperateResult<List<Daybook>>(ErrorCode.PARAM_ERROR,"参数错误");
		}

		return daybookService.getMyDaybook(uid, page, limit);
	}
	
	@RequestMapping("/get")
	public OperateResult<Daybook> get(@RequestBody  String id) {
		if(TextUtils.isEmpty(id))return new OperateResult< Daybook>(ErrorCode.PARAM_ERROR,"参数错误");
		return daybookService.get(id);
	}
	
	@RequestMapping("/del")
	public OperateResult<Object> del(@RequestBody  String id) {
		if(TextUtils.isEmpty(id))return new OperateResult< Object>(ErrorCode.PARAM_ERROR,"参数错误");
		return daybookService.del(id);
	}
	
	@RequestMapping("/get_category")
	public OperateResult<List<Category>> getCategory() {
		return daybookService.getCategory();
	}
}
