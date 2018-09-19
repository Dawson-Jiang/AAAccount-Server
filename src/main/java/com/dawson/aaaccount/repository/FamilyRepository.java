package com.dawson.aaaccount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
 
import com.dawson.aaaccount.entity.Family;

public interface FamilyRepository extends CrudRepository<Family, String>,JpaSpecificationExecutor<Family> {
 List<Object[]> getFamilyByMember(String hql);
}
