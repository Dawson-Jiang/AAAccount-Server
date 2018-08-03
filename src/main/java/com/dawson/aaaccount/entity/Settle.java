package com.dawson.aaaccount.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Settle {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle.id
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle.money
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    private BigDecimal money;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle.date
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    private Date date;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle.family
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    private String family;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle.creator
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    private String creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle.start_date
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    private Date startDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle.end_date
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    private Date endDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle.settled
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    private Byte settled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle.settlecol
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    private String settlecol;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle.id
     *
     * @return the value of settle.id
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle.id
     *
     * @param id the value for settle.id
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle.money
     *
     * @return the value of settle.money
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle.money
     *
     * @param money the value for settle.money
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle.date
     *
     * @return the value of settle.date
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle.date
     *
     * @param date the value for settle.date
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle.family
     *
     * @return the value of settle.family
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    public String getFamily() {
        return family;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle.family
     *
     * @param family the value for settle.family
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    public void setFamily(String family) {
        this.family = family == null ? null : family.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle.creator
     *
     * @return the value of settle.creator
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle.creator
     *
     * @param creator the value for settle.creator
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle.start_date
     *
     * @return the value of settle.start_date
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle.start_date
     *
     * @param startDate the value for settle.start_date
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle.end_date
     *
     * @return the value of settle.end_date
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle.end_date
     *
     * @param endDate the value for settle.end_date
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle.settled
     *
     * @return the value of settle.settled
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    public Byte getSettled() {
        return settled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle.settled
     *
     * @param settled the value for settle.settled
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    public void setSettled(Byte settled) {
        this.settled = settled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle.settlecol
     *
     * @return the value of settle.settlecol
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    public String getSettlecol() {
        return settlecol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle.settlecol
     *
     * @param settlecol the value for settle.settlecol
     *
     * @mbg.generated Sun Jul 29 17:49:52 CST 2018
     */
    public void setSettlecol(String settlecol) {
        this.settlecol = settlecol == null ? null : settlecol.trim();
    }
}