package com.dawson.aaaccount.domain;

import com.dawson.aaaccount.entity.FamilyMember;

public interface FamilyMemberMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table family_member
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table family_member
     *
     * @mbg.generated
     */
    int insert(FamilyMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table family_member
     *
     * @mbg.generated
     */
    int insertSelective(FamilyMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table family_member
     *
     * @mbg.generated
     */
    FamilyMember selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table family_member
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(FamilyMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table family_member
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(FamilyMember record);
}