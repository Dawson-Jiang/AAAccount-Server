package com.dawson.aaaccount.domain;

import com.dawson.aaaccount.entity.SystemLog;

public interface SystemLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_log
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_log
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    int insert(SystemLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_log
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    int insertSelective(SystemLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_log
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    SystemLog selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_log
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    int updateByPrimaryKeySelective(SystemLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_log
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    int updateByPrimaryKey(SystemLog record);
}