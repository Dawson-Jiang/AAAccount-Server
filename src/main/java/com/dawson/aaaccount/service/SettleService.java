package com.dawson.aaaccount.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.Settle;

public interface SettleService {

	OperateResult<String> settle(Settle settle);

	OperateResult<List<Settle>> getFamilySettle(String fid);

	OperateResult<Settle> statistic(String fid, Date start, Date end);

	OperateResult<Settle> statisticUnSettled(String fid);

	OperateResult<Settle> statisticMine(String uid, Date start, Date end);
}
