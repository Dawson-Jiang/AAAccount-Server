package com.dawson.aaaccount.domain;

import com.dawson.aaaccount.entity.LoginInfo;

public interface LoginInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_info
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_info
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    int insert(LoginInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_info
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    int insertSelective(LoginInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_info
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    LoginInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_info
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    int updateByPrimaryKeySelective(LoginInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_info
     *
     * @mbg.generated Sun Jul 22 22:01:16 CST 2018
     */
    int updateByPrimaryKey(LoginInfo record);
}