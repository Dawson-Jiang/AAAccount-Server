package com.dawson.aaaccount.service;

import java.util.List;

import com.dawson.aaaccount.entity.Category;
import com.dawson.aaaccount.entity.Daybook;
import com.dawson.aaaccount.entity.OperateResult;


public interface DaybookService {
	 OperateResult<Daybook> get(String id);
	 OperateResult<List<Daybook>> getFamilyDaybook(String fid,int page,int limit);
	 OperateResult<List<Daybook>> getMyDaybook(String uid,int page,int limit);
	 OperateResult<String>  save(Daybook daybook);
	 OperateResult<List<Category>> getCategory();
	 OperateResult<Object> del(String id);
}
