package com.dawson.aaaccount.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "settle")
public class Settle  implements Serializable {
	private static final long serialVersionUID = 7419229779735784896L;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle.id
     *
     * @mbg.generated
     */
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle.money
     *
     * @mbg.generated
     */
    private BigDecimal money;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle.date
     *
     * @mbg.generated
     */
    private Date date;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle.family_id
     *
     * @mbg.generated
     */
    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle.creator_id
     *
     * @mbg.generated
     */
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle.start_date
     *
     * @mbg.generated
     */
    private Date startDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle.end_date
     *
     * @mbg.generated
     */
    private Date endDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle.settled
     *
     * @mbg.generated
     */
    private Byte settled;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "settle",fetch = FetchType.EAGER)
    private List<SettleDetail> details;
    

    public List<SettleDetail> getDetails() {
		return details;
	}

	public void setDetails(List<SettleDetail> details) {
		this.details = details;
	}

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle.id
     *
     * @return the value of settle.id
     *
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle.family_id
     *
     * @return the value of settle.family_id
     *
     * @mbg.generated
     */
    public Family getFamily() {
        return family;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle.family_id
     *
     * @param familyId the value for settle.family_id
     *
     * @mbg.generated
     */
    public void setFamily(Family family) {
        this.family = family;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle.creator_id
     *
     * @return the value of settle.creator_id
     *
     * @mbg.generated
     */
    public User getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle.creator_id
     *
     * @param creatorId the value for settle.creator_id
     *
     * @mbg.generated
     */
    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle.start_date
     *
     * @return the value of settle.start_date
     *
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
     */
    public void setSettled(Byte settled) {
        this.settled = settled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle.create_time
     *
     * @return the value of settle.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle.create_time
     *
     * @param createTime the value for settle.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle.update_time
     *
     * @return the value of settle.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle.update_time
     *
     * @param updateTime the value for settle.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}