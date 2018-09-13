package com.dawson.aaaccount.repository;
 
import org.springframework.data.repository.CrudRepository; 

import com.dawson.aaaccount.entity.User;
  
public interface UserRepository extends CrudRepository<User,String> { 
	User findByOpenid(String openId);
}
