package com.dawson.aaaccount.entity;

import java.util.Date;

public class LoginInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_info.id
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_info.last_login_time
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    private Date lastLoginTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_info.version
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    private String version;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_info.platform
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    private String platform;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_info.phone
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_info.phone_type
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    private String phoneType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_info.operate
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    private String operate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_info.user
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    private String user;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_info.id
     *
     * @return the value of login_info.id
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_info.id
     *
     * @param id the value for login_info.id
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_info.last_login_time
     *
     * @return the value of login_info.last_login_time
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_info.last_login_time
     *
     * @param lastLoginTime the value for login_info.last_login_time
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_info.version
     *
     * @return the value of login_info.version
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    public String getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_info.version
     *
     * @param version the value for login_info.version
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_info.platform
     *
     * @return the value of login_info.platform
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_info.platform
     *
     * @param platform the value for login_info.platform
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_info.phone
     *
     * @return the value of login_info.phone
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_info.phone
     *
     * @param phone the value for login_info.phone
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_info.phone_type
     *
     * @return the value of login_info.phone_type
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    public String getPhoneType() {
        return phoneType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_info.phone_type
     *
     * @param phoneType the value for login_info.phone_type
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType == null ? null : phoneType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_info.operate
     *
     * @return the value of login_info.operate
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    public String getOperate() {
        return operate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_info.operate
     *
     * @param operate the value for login_info.operate
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    public void setOperate(String operate) {
        this.operate = operate == null ? null : operate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_info.user
     *
     * @return the value of login_info.user
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    public String getUser() {
        return user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_info.user
     *
     * @param user the value for login_info.user
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }
}