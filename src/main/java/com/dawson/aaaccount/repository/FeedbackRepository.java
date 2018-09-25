package com.dawson.aaaccount.repository;
 

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
 
import com.dawson.aaaccount.entity.Feedback;

public interface FeedbackRepository extends CrudRepository<Feedback, String>,JpaSpecificationExecutor<Feedback> {
	  
	}
