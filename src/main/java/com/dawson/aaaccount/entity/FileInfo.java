package com.dawson.aaaccount.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fileinfo")
public class FileInfo extends BaseEntity {
	private static final long serialVersionUID = 7419229896237464702L;

	public FileInfo() {
	}

	public FileInfo(String id) {
		super(id);
	}
	
	private String name;
	private String url;
	/**
	 * 缩略图
	 */
	private String thUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThUrl() {
		return thUrl;
	}

	public void setThUrl(String thUrl) {
		this.thUrl = thUrl;
	}
	
	@Override
	public FileInfo cleanClone() {
 		return new FileInfo(getId());
	}
}
