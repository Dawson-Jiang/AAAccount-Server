package com.dawson.aaaccount.domain;

import java.util.List;

import com.dawson.aaaccount.entity.DayBook;

public interface DaybookDao {
	  List<DayBook> getAll();
	  void  insert(DayBook daybook);
}
