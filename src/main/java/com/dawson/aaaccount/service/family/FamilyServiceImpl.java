package com.dawson.aaaccount.service.family;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
 
import com.dawson.aaaccount.entity.Family;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.repository.FamilyRepository;

@Service("familyService")
public class FamilyServiceImpl implements FamilyService {

	@Resource
	private FamilyRepository familyDao;
	
	@Override
	public OperateResult<Family> getFamily(String id) {
 		return new OperateResult<Family>( familyDao.findById(id).get());
	}

	@Override
	public OperateResult<List<Family>> getFamilyByUserId(String userid) {
		return new OperateResult<List<Family>>(null);//TODO
	}
}
