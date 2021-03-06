package com.dawson.aaaccount.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.dawson.aaaccount.entity.Settle;

public interface SettleRepository extends CrudRepository<Settle, String>,JpaSpecificationExecutor<Settle>{

}
