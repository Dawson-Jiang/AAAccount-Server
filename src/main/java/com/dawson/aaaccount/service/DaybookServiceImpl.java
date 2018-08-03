package com.dawson.aaaccount.service;

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

		AVQuery<AVObject> query = new AVQuery<AVObject>(CLASS_NAME);
//		            if (TextUtils.isEmpty(familyId)) {
		                query.whereEqualTo(RECORDER, AVUser.createWithoutData("_User", "5b6004ab808ca4006fe99fd6"));
//		     		                query.whereDoesNotExist(FAMILY);
//		            } else {
//		                val family = AVObject.createWithoutData(DataObjectHelper.FAMILY.CLASS_NAME, familyId)
//		                query.whereEqualTo(DataObjectHelper.DAY_BOOK.FAMILY, family)
//		            }
		query.limit(10);// 最多返回 10 条结果
		query.skip(0 * 10);// 跳过 20 条结果
		query.include(CONSUME_CATEGORY + "." + NAME).include(PAYER + "." + UNAME).include(PAYER2 + "." + MNAME);
		query.selectKeys(Arrays.asList(MONEY, CONSUME_CATEGORY + "." + NAME, PAYER + "." + UNAME, PAYER2 + "." + MNAME,
				DATE, THUM_PICTURES));
		query.orderByDescending(DATE);
		List<AVObject> objs;
		try {
			objs = query.find();
		} catch (AVException e) {
			e.printStackTrace();
			return null;
		}
		List<Daybook> daybooks = new ArrayList<Daybook>();
		objs.forEach(new Consumer<AVObject>() {

			@Override
			public void accept(AVObject avObject) {

				Daybook daybook = new Daybook();
				daybook.setId(avObject.getObjectId());

				daybook.setCategory(avObject.getAVObject(CONSUME_CATEGORY).getObjectId());
				AVUser payer = avObject.getAVUser(PAYER);
				if (payer != null)
					daybook.setPayer(payer.getObjectId());
				else {
					AVObject payer2 = avObject.getAVObject(PAYER2);
					if (payer2 != null)
						daybook.setPayer(payer2.getObjectId());
				}
				daybook.setDate(avObject.getDate(DATE));
				daybook.setMoney(BigDecimal.valueOf(avObject.getDouble(MONEY)));
//				String thumbPics = avObject.getString(THUM_PICTURES);
//		                        		daybook.thumbPictures =Arrays.asList( thumbPics.split(";"));
		
				daybooks.add(daybook);

			}

		});
		return daybooks;
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
