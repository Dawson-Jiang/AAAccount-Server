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


	@RequestMapping("/add_daybook")
	public OperateResult<String> addDaybook(@RequestBody Daybook body) {
		return daybookService.addDaybook(body);
	}
	
	@RequestMapping("/get_daybook")
	public OperateResult<List<Daybook>> getDaybook(@RequestBody Map<String, String> body) {
		String fid = null;
		String uid = null;
		int page = 0;
		int limit = 10;
		try {
			fid = body.get("family_id");
		    uid=  body.get("recorder_id");
		    if(fid==null&& uid==null)	return new OperateResult<List<Daybook>>(ErrorCode.PARAM_ERROR,"参数错误");
			page = Integer.parseInt(body.get("page"));
			limit = Integer.parseInt(body.get("limit"));
		} catch (Exception e) {
			return new OperateResult<List<Daybook>>(ErrorCode.PARAM_ERROR,"参数错误");
		}

		return daybookService.getDaybook(fid,uid, page, limit);
	}
	
	@RequestMapping("/get_category")
	public OperateResult<List<Category>> getCategory() {
		return daybookService.getCategory();
	}

	@RequestMapping("/getusers")
	public List<User> getUsers() {
		AVQuery<AVUser> query = new AVQuery<AVUser>("_User");

//		AVUser.loginWithAuthData(userInfo, callback);
//        if (TextUtils.isEmpty(familyId)) {
//            query.whereEqualTo(RECORDER, AVUser.createWithoutData("_User", "5b6004ab808ca4006fe99fd6"));
// 		                query.whereDoesNotExist(FAMILY);
//        } else {
//            val family = AVObject.createWithoutData(DataObjectHelper.FAMILY.CLASS_NAME, familyId)
//            query.whereEqualTo(DataObjectHelper.DAY_BOOK.FAMILY, family)
//        }
		query.limit(10);// 最多返回 10 条结果
		query.skip(0 * 10);// 跳过 20 条结果
//query.include(CONSUME_CATEGORY + "." + NAME).include(PAYER + "." + UNAME).include(PAYER2 + "." + MNAME);
//query.selectKeys(Arrays.asList(MONEY, CONSUME_CATEGORY + "." + NAME, PAYER + "." + UNAME, PAYER2 + "." + MNAME,
//	DATE, THUM_PICTURES));
		query.orderByDescending("updatedAt");
		List<AVUser> objs;
		try {
			objs = query.find();
		} catch (AVException e) {
			e.printStackTrace();
			return null;
		}
		List<User> users = new ArrayList<User>();
		objs.forEach(new Consumer<AVUser>() {

			@Override
			public void accept(AVUser avObject) {

				User duser = new User();
				duser.setId(avObject.getObjectId());
				duser.setCreateTime(avObject.getCreatedAt());
				duser.setUpdateTime(avObject.getUpdatedAt());
				duser.setName(avObject.getUsername());
				duser.setAuthData(avObject.getJSONObject("authData").toString());
//	String thumbPics = avObject.getString(THUM_PICTURES);
//                    		daybook.thumbPictures =Arrays.asList( thumbPics.split(";"));
				users.add(duser);
			}

		});
		return users;
	}
}
