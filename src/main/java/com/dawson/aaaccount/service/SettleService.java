package com.dawson.aaaccount.service;

import java.util.List;
import java.util.Map;

import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.Settle;

public interface SettleService {

	OperateResult<String> settle( Settle settle);

	OperateResult<List<Settle>> getFamilySettle( Map<String, String> param);

	OperateResult<Settle> statistic( Map<String, String> param);

	OperateResult<Settle> statisticMine( Map<String, String> param);
}
