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

import com.dawson.aaaccount.domain.DaybookMapper;
import com.dawson.aaaccount.entity.Daybook;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;

@Service("daybookService")
public class DaybookServiceImpl implements DaybookService {
	private static String CLASS_NAME = "DayBook";
	private static String CONSUME_CATEGORY = "consumeCategory";
	private static String FAMILY = "family";
	private static String RECORDER = "recorder";
	private static String CONSUMER = "consumers";
	private static String CONSUMER2 = "consumers2";
	private static String PAYER = "payer";
	private static String PAYER2 = "payer2";
	private static String SETTLE = "settled";
	private static String MONEY = "money";
	private static String PICTURES = "pictures";
	private static String THUM_PICTURES = "thumPictures";
	private static String DESCRIPTION = "description";
	private static String DATE = "date";

	private static String UNAME = "username";
	private static String MNAME = "memberName";

	private static String NAME = "name";
	@Resource
	private DaybookMapper daybookDao;

	@Override
	public List<Daybook> getAll() {
		return null;
	}

	@Override
	public void addDaybook(Daybook daybook) {
		Daybook tdb = new Daybook();
//		tdb.setId(UUID.randomUUID().toString());
		tdb.setDes("买菜");
		double money = new Random().nextDouble() * 100;
//		money = (double)Math.round(money * 100) / 100D;
		BigDecimal dmoney = new BigDecimal(money);
		dmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
		tdb.setMoney(dmoney);
		daybookDao.insert(tdb);
	}
}
