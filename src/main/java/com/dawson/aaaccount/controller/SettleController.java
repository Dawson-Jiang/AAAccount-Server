package com.dawson.aaaccount.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawson.aaaccount.entity.Daybook;
import com.dawson.aaaccount.entity.ErrorCode;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.Settle;
import com.dawson.aaaccount.service.SettleService;

@RestController
@RequestMapping("/settle")
public class SettleController {

	@Resource
	private SettleService settleService;

	@RequestMapping("/settle")
	public OperateResult<String> settle(@RequestBody Settle settle) {
		if(settle==null)return new OperateResult<String>(ErrorCode.PARAM_ERROR, "参数错误");
		return  settleService.settle(settle);
	}

	@RequestMapping("/get_family_settle")
	public OperateResult<List<Settle>> getFamilySettle(@RequestBody Map<String, String> param) {
		if (param.containsKey("fid")) {
			String fid = param.get("fid");
			return settleService.getFamilySettle(fid);
		} else
			return new OperateResult<List<Settle>>(ErrorCode.PARAM_ERROR, "参数错误");
	}

	@RequestMapping("/statistic")
	public OperateResult<Settle> statistic(@RequestBody Map<String, String> param) {
		String fid = param.get("fid");
		Date start = null, end = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		try {
			start = simpleDateFormat.parse(param.get("start"));
			end = simpleDateFormat.parse(param.get("end"));
		} catch (ParseException e) {
			e.printStackTrace();
			return new OperateResult<Settle>(ErrorCode.PARAM_ERROR, "参数错误");
		}
		return settleService.statistic(fid, start, end);
	}

	@RequestMapping("/statistic_mine")
	public OperateResult<Settle> statisticMine(@RequestBody Map<String, String> param) {
		String uid = param.get("uid");
		Date start = null, end = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		try {
			start = simpleDateFormat.parse(param.get("start"));
			end = simpleDateFormat.parse(param.get("end"));
		} catch (ParseException e) {
			e.printStackTrace();
			return new OperateResult<Settle>(ErrorCode.PARAM_ERROR, "参数错误");
		}
		return settleService.statisticMine(uid, start, end);
	}
}
