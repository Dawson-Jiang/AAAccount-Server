package com.dawson.aaaccount.service;

import java.util.List;

import com.dawson.aaaccount.entity.Family;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.User;

public interface FamilyService {
	OperateResult<Family> get(String id);
	OperateResult<List<Family>> getMyFamily(String userid);
 
	OperateResult<String> save(Family family);

	OperateResult<String> join(String fid,User user);
	
	OperateResult<Object> disJoin(String fid,String uid);
	
	OperateResult<Object> del(String fid); 
}
