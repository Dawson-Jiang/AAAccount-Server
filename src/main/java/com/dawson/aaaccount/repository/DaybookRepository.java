package com.dawson.aaaccount.repository;

import org.springframework.data.repository.CrudRepository;

import com.dawson.aaaccount.entity.Daybook; 

public interface DaybookRepository extends CrudRepository<Daybook, String> {

}
