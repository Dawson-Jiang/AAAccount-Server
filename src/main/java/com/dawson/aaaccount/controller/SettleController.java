package com.dawson.aaaccount.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		return new OperateResult<String>("");
	}
	
	
	@RequestMapping("/get_family_settle")
	public OperateResult<List<Settle>> getFamilySettle(@RequestBody  Map<String,String> param) {
		return new OperateResult<List<Settle>>(null);
	}
	
	
	
	@RequestMapping("/statistic")
	public OperateResult<Settle> statistic(@RequestBody  Map<String,String> param) {
		return new OperateResult<Settle>(null);
	}
	
	
	@RequestMapping("/statistic_mine")
	public OperateResult<Settle> statisticMine(@RequestBody  Map<String,String> param) {
		return new OperateResult<Settle>(null);
	} 
}
