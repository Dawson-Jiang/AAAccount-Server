package com.dawson.aaaccount.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.util.TextUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawson.aaaccount.entity.Feedback;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.User;
import com.dawson.aaaccount.service.FeedbackService;
import com.dawson.aaaccount.utils.CommonUtils;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	@Resource
	private FeedbackService fService;

	@RequestMapping("/add")
	OperateResult<String> add(@RequestBody Map<String, String> param) {
		Feedback feedback = new Feedback();
		try {
			feedback.setTitle(param.get("title"));
			feedback.setContent(param.get("content"));
			User user = new User(true);
			user.setId(param.get("uid"));
			feedback.setUser(user);
		} catch (Exception e) {
			return CommonUtils.<String>getParamError();
		}
		return fService.add(feedback);
	}

	@RequestMapping("/get_my_feedback")
	OperateResult<List<Feedback>> getByUser(@RequestBody Map<String, String> param) {
		String uid = param.get("uid");
		if (TextUtils.isEmpty(uid))
			return CommonUtils.<List<Feedback>>getParamError();
		User user = new User();
		user.setId(uid);
		return fService.getByUser(user);
	}
}
