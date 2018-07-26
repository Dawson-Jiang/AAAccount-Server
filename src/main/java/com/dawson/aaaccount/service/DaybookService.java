package com.dawson.aaaccount.service;

import java.util.List;

import com.dawson.aaaccount.entity.DayBook;

public interface DaybookService {
	  List<DayBook> getAll();
	  void  addDayBook(DayBook daybook);
}
