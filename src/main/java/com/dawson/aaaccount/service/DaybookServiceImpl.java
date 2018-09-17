package com.dawson.aaaccount.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dawson.aaaccount.entity.Category;
import com.dawson.aaaccount.entity.Daybook;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.repository.CategoryRepository;
import com.dawson.aaaccount.repository.DaybookRepository; 

@Service("daybookService")
public class DaybookServiceImpl implements DaybookService { 
	@Resource
	private DaybookRepository daybookRepository;


	@Override
	public OperateResult<String> save(Daybook daybook) {
		int res = daybookRepository.save(daybook)==null?0:1;
		if (res > 0)
			return new OperateResult<String>(daybook.getId());
		else
			return new OperateResult<String>(0, "添加失败");
	}

	@Resource
	private CategoryRepository categoryRepository;

	@Override
	public OperateResult<List<Category>> getCategory() {
		try {
			return new OperateResult<>((List<Category>)categoryRepository.findAll());
		} catch (Exception e) {
			return new OperateResult<>(0, "操作失败");
		}
	}

	@Override
	public OperateResult<Daybook> get(String id) {
	 
		try {
			
			return new OperateResult<Daybook>(null);//TODO
		} catch (Exception e) {
			return new OperateResult<>(0, "查询失败");
		} 
	}

	@Override
	public OperateResult<List<Daybook>> getFamilyDaybook(String fid, int page, int limit) {
		int offset = page * limit;
		try {
 
			return new OperateResult<List<Daybook>>(new ArrayList<>());//TODO
		} catch (Exception e) {
			return new OperateResult<>(0, "查询失败");
		}
	}

	@Override
	public OperateResult<List<Daybook>> getMyDaybook(String uid, int page, int limit) {
		int offset = page * limit;
		try {
		 
			return new OperateResult<List<Daybook>>(new ArrayList<>());//TODO
		} catch (Exception e) {
			return new OperateResult<>(0, "查询失败");
		}
	}

	@Override
	public OperateResult<Object> del(String id) {
		try {
		 	daybookRepository.deleteById(id);	 
		return new  OperateResult<Object>( 1);	 

		} catch (Exception e) {
			return new  OperateResult<Object>(0,e.getMessage());	 
		}
 	}
}
