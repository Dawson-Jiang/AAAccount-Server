package com.dawson.aaaccount.service;

import java.util.List;

import com.dawson.aaaccount.entity.Daybook;


public interface DaybookService {
	  List<Daybook> getAll();
	  void  addDaybook(Daybook daybook);
}
