package com.dawson.aaaccount.service;

import java.util.List;

import com.dawson.aaaccount.entity.Feedback;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.User;

public interface FeedbackService {
	OperateResult<String> add(Feedback feedback);

	OperateResult<List<Feedback>> getByUser(User user);

}
