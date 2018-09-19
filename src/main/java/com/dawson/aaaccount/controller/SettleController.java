package com.dawson.aaaccount.controller;

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
	public OperateResult<Object> name(@RequestBody Settle settle) {
		return new OperateResult<Object>(0);
	}
}
