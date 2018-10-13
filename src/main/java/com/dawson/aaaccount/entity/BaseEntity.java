package com.dawson.aaaccount.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	public BaseEntity() {
	}

	public BaseEntity(String id) {
		this(true);
		this.id = id;
	}

	public BaseEntity(boolean isclean) {
		if (isclean) {
			setCreateTime(null);
			setUpdateTime(null);
		}
	}

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date createTime = new Date();
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date updateTime = new Date();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public abstract BaseEntity   cleanClone ();
}
