package com.dawson.aaaccount.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "user")
public class User extends BaseEntity {
	private static final long serialVersionUID = 7419229779731522702L;

	public User() {
	} 
	
	public User(String id) {
		super(id);
	}

	private String name;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String headPic;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String authData;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String email;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String phone;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date lastLoginTime;

	@OneToOne
	@JoinColumn(name = "login_info_id")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LoginInfo loginInfo;

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String token;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String openid;
	
	 
	private boolean isMember;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic == null ? null : headPic.trim();
	}

	public String getAuthData() {
		return authData;
	}

	public void setAuthData(String authData) {
		this.authData = authData == null ? null : authData.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token == null ? null : token.trim();
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid == null ? null : openid.trim();
	}

	public boolean isMember() {
		return isMember;
	}

	public void setMember(boolean isMember) {
		this.isMember = isMember;
	}

	@Override
	public User cleanClone() {
		return new User(getId());
	}
}