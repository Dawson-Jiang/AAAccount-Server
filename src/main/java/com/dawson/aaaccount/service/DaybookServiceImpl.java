package com.dawson.aaaccount.service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dawson.aaaccount.domain.DaybookDao;
import com.dawson.aaaccount.entity.DayBook;

@Service("daybookService")
public class DaybookServiceImpl implements DaybookService {

	@Resource
	private DaybookDao daybookDao;

	@Override
	public List<DayBook> getAll() {

		return daybookDao.getAll();
	}

	@Override
	public void addDayBook(DayBook daybook) {
		DayBook tdb = new DayBook();
		tdb.setId(UUID.randomUUID().toString());
		tdb.setDescription("买菜");
		double money = new Random().nextDouble() * 100;
		money = (double)Math.round(money * 100) / 100;
		tdb.setMoney(money);
		daybookDao.insert(tdb);
	}

}
