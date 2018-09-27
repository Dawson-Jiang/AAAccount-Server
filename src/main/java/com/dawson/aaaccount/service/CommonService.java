package com.dawson.aaaccount.service;

import com.dawson.aaaccount.entity.OperateResult;

public interface CommonService { 
OperateResult<String> syncUserFromLeancloud();
OperateResult<String> syncMemberFromLeancloud();
OperateResult<String> syncCategoryFromLeancloud();
OperateResult<String> syncFamilyFromLeancloud();
OperateResult<String> syncDaybookFromLeancloud();
}
