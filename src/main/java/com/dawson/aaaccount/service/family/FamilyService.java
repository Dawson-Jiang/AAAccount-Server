package com.dawson.aaaccount.service.family;

import java.util.List;

import com.dawson.aaaccount.entity.Family;
import com.dawson.aaaccount.entity.OperateResult;

public interface FamilyService {
	OperateResult<Family> getFamily(String id);
	OperateResult<List<Family>> getFamilyByUserId(String userid);

}
