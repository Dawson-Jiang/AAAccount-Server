package com.dawson.aaaccount.repository;

import org.springframework.data.repository.CrudRepository;
import com.dawson.aaaccount.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, String> {

}
