package com.dawson.aaaccount.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.web.bind.annotation.RequestMapping;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.dawson.aaaccount.entity.User;

public class CommonController {

	
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
