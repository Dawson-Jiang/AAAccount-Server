package com.dawson.aaaccount.service.family;

import java.util.List;

import com.dawson.aaaccount.entity.Family;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.User;

public interface FamilyService {
	OperateResult<Family> get(String id);
	OperateResult<List<Family>> getMyFamily(String userid);
 
	OperateResult<String> save(Family family);

	OperateResult<Object> join(String fid,String uid);
	
	OperateResult<Object> disJoin(String fid,String uid);
	
	OperateResult<Object> del(String fid);
	
	OperateResult<User> addMember(Family family);
	
	OperateResult<Object> delMemeber(String fid,String uid);
}
