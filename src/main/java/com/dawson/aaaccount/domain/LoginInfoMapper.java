package com.dawson.aaaccount.domain;

import com.dawson.aaaccount.entity.LoginInfo;

public interface LoginInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_info
     *
     * @mbg.generated
     */
    int insert(LoginInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_info
     *
     * @mbg.generated
     */
    int insertSelective(LoginInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_info
     *
     * @mbg.generated
     */
    LoginInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LoginInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LoginInfo record);
    
    LoginInfo selectByUserId(String userid);
}