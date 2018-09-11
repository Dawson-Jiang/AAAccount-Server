package com.dawson.aaaccount.service.family;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dawson.aaaccount.domain.FamilyMapper;
import com.dawson.aaaccount.entity.Family;
import com.dawson.aaaccount.entity.OperateResult;

@Service("familyService")
public class FamilyServiceImpl implements FamilyService {

	@Resource
	private FamilyMapper familyDao;
	
	@Override
	public OperateResult<Family> getFamily(String id) {
 		return new OperateResult<Family>( familyDao.selectByPrimaryKey(id));
	}

	@Override
	public OperateResult<List<Family>> getFamilyByUserId(String userid) {
		return new OperateResult<List<Family>>( familyDao.selectByUserId(userid));
	}
}
