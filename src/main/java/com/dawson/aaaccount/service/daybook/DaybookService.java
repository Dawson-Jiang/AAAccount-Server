package com.dawson.aaaccount.service.daybook;

import java.util.List;

import com.dawson.aaaccount.entity.Category;
import com.dawson.aaaccount.entity.Daybook;
import com.dawson.aaaccount.entity.OperateResult;


public interface DaybookService {
	 OperateResult<List<Daybook>> getDaybook(String fid,String uid,int page,int limit);
	 OperateResult<String>  addDaybook(Daybook daybook);
	 
	 OperateResult<List<Category>> getCategory( );

}
