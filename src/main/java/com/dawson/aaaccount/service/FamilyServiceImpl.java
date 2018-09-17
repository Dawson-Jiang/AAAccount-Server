package com.dawson.aaaccount.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
 
import com.dawson.aaaccount.entity.Family;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.User;
import com.dawson.aaaccount.repository.FamilyRepository;

@Service("familyService")
public class FamilyServiceImpl implements FamilyService {

	@Resource
	private FamilyRepository familyDao;
	
	@Override
	public OperateResult<Family> get(String id) {
 		return new OperateResult<Family>( familyDao.findById(id).get());
	}

 

	@Override
	public OperateResult<List<Family>> getMyFamily(String userid) {
		return new OperateResult<List<Family>>(null);//TODO
	}

	@Override
	public OperateResult<String> save(Family family) {
		Family res= familyDao.save(family);
		if(res==null)return new OperateResult<String>();
		else return new OperateResult<String>(res.getId());
	}



	@Override
	public OperateResult<Object> join(String fid, String uid) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public OperateResult<Object> disJoin(String fid, String uid) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public OperateResult<Object> del(String fid) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public OperateResult<User> addMember(Family family) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public OperateResult<Object> delMemeber(String fid, String uid) {
		// TODO Auto-generated method stub
		return null;
	}
}
