package com.dawson.aaaccount.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {
	private static final long serialVersionUID = 7419229779739851545L;

	public Category() {
	}

	public Category(String id) {
		super(id);
	}

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String name;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer orderFlag;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(Integer orderFlag) {
		this.orderFlag = orderFlag;
	}

	@Override
	public Category cleanClone() {
		return new Category(getId());
	}
}