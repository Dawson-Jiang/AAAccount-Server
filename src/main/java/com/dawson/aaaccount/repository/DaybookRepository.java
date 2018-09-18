package com.dawson.aaaccount.repository;
 
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dawson.aaaccount.entity.Daybook; 

public interface DaybookRepository extends PagingAndSortingRepository<Daybook, String>,JpaSpecificationExecutor<Daybook> {

}
