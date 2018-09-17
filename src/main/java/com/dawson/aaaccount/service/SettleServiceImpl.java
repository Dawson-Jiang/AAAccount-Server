package com.dawson.aaaccount.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dawson.aaaccount.repository.SettleRepository;

@Service("settleService")
public class SettleServiceImpl implements SettleService {

	@Resource
	private SettleRepository settleRepository;
}
