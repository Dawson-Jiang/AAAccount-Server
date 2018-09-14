package com.dawson.aaaccount.repository;

import org.springframework.data.repository.CrudRepository;

import com.dawson.aaaccount.entity.LoginInfo;
import com.dawson.aaaccount.entity.User;

public interface LoginInfoRepository extends CrudRepository<LoginInfo,Integer> { 
 
}

