package com.dawson.aaaccount.service.daybook;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Consumer;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dawson.aaaccount.domain.CategoryMapper;
import com.dawson.aaaccount.domain.DaybookMapper;
import com.dawson.aaaccount.entity.Category;
import com.dawson.aaaccount.entity.Daybook;
import com.dawson.aaaccount.entity.OperateResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;

@Service("daybookService")
public class DaybookServiceImpl implements DaybookService {
//	private static String CLASS_NAME = "DayBook";
//	private static String CONSUME_CATEGORY = "consumeCategory";
//	private static String FAMILY = "family";
//	private static String RECORDER = "recorder";
//	private static String CONSUMER = "consumers";
//	private static String CONSUMER2 = "consumers2";
//	private static String PAYER = "payer";
//	private static String PAYER2 = "payer2";
//	private static String SETTLE = "settled";
//	private static String MONEY = "money";
//	private static String PICTURES = "pictures";
//	private static String THUM_PICTURES = "thumPictures";
//	private static String DESCRIPTION = "description";
//	private static String DATE = "date";
//
//	private static String UNAME = "username";
//	private static String MNAME = "memberName";
//
//	private static String NAME = "name";
	@Resource
	private DaybookMapper daybookDao;

	@Override
	public OperateResult<List<Daybook>> getDaybook(String fid, String uid, int page, int limit) {
		int offset = page * limit;
		try {
			if(fid==null|| "".equals(fid)) fid=null;
			if(uid==null|| "".equals(uid)) uid=null;
			return new OperateResult<>(daybookDao.selectByHost(fid, uid, offset, limit));
		} catch (Exception e) {
			return new OperateResult<>(0, "查询失败");
		}
	}

	@Override
	public OperateResult<String> addDaybook(Daybook daybook) {
		int res = daybookDao.insertSelective(daybook);
		if (res > 0)
			return new OperateResult<String>(daybook.getId());
		else
			return new OperateResult<String>(0, "添加失败");
	}

	@Resource
	private CategoryMapper categoryDao;

	@Override
	public OperateResult<List<Category>> getCategory() {
		try {
			return new OperateResult<>(categoryDao.selectAll());
		} catch (Exception e) {
			return new OperateResult<>(0, "操作失败");
		}
	}
}
