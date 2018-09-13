package com.dawson.aaaccount.service.daybook;

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
	private DaybookRepository daybookDao;

	@Override
	public OperateResult<List<Daybook>> getDaybook(String fid, String uid, int page, int limit) {
		int offset = page * limit;
		try {
			if(fid==null|| "".equals(fid)) fid=null;
			if(uid==null|| "".equals(uid)) uid=null;
			return new OperateResult<List<Daybook>>(new ArrayList<>());//TODO
		} catch (Exception e) {
			return new OperateResult<>(0, "查询失败");
		}
	}

	@Override
	public OperateResult<String> addDaybook(Daybook daybook) {
		int res = daybookDao.save(daybook)==null?0:1;
		if (res > 0)
			return new OperateResult<String>(daybook.getId());
		else
			return new OperateResult<String>(0, "添加失败");
	}

	@Resource
	private CategoryRepository categoryDao;

	@Override
	public OperateResult<List<Category>> getCategory() {
		try {
			return new OperateResult<>((List<Category>)categoryDao.findAll());
		} catch (Exception e) {
			return new OperateResult<>(0, "操作失败");
		}
	}
}
