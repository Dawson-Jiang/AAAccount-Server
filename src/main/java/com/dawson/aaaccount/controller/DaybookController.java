package com.dawson.aaaccount.controller;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawson.aaaccount.entity.DayBook;
import com.dawson.aaaccount.service.DaybookService;

@RestController
@RequestMapping("/daybook")
public class DaybookController {

	@Resource
	private DaybookService daybookService;
	
	@RequestMapping("/getall")
	public List<DayBook> getAll(){ 
		daybookService.addDayBook(null);
		return daybookService.getAll();
	}
}
