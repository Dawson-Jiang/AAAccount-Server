package com.dawson.aaaccount.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "system_log")
public class SystemLog implements Serializable {
	private static final long serialVersionUID = 7419229779731525896L;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_log.id
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
     * This field corresponds to the database column system_log.exception
     *
     * @mbg.generated
     */
    private String exception;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_log.user_id
     *
     * @mbg.generated
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_log.detail
     *
     * @mbg.generated
     */
    private String detail;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_log.fix_status
     *
     * @mbg.generated
     */
    private Byte fixStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_log.location
     *
     * @mbg.generated
     */
    private String location;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_log.date
     *
     * @mbg.generated
     */
    private Date date;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_log.upload_time
     *
     * @mbg.generated
     */
    private Date uploadTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_log.platform
     *
     * @mbg.generated
     */
    private String platform;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_log.type
     *
     * @mbg.generated
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_log.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_log.id
     *
     * @return the value of system_log.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_log.id
     *
     * @param id the value for system_log.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_log.exception
     *
     * @return the value of system_log.exception
     *
     * @mbg.generated
     */
    public String getException() {
        return exception;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_log.exception
     *
     * @param exception the value for system_log.exception
     *
     * @mbg.generated
     */
    public void setException(String exception) {
        this.exception = exception == null ? null : exception.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_log.detail
     *
     * @return the value of system_log.detail
     *
     * @mbg.generated
     */
    public String getDetail() {
        return detail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_log.detail
     *
     * @param detail the value for system_log.detail
     *
     * @mbg.generated
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_log.fix_status
     *
     * @return the value of system_log.fix_status
     *
     * @mbg.generated
     */
    public Byte getFixStatus() {
        return fixStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_log.fix_status
     *
     * @param fixStatus the value for system_log.fix_status
     *
     * @mbg.generated
     */
    public void setFixStatus(Byte fixStatus) {
        this.fixStatus = fixStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_log.location
     *
     * @return the value of system_log.location
     *
     * @mbg.generated
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_log.location
     *
     * @param location the value for system_log.location
     *
     * @mbg.generated
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_log.date
     *
     * @return the value of system_log.date
     *
     * @mbg.generated
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_log.date
     *
     * @param date the value for system_log.date
     *
     * @mbg.generated
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_log.upload_time
     *
     * @return the value of system_log.upload_time
     *
     * @mbg.generated
     */
    public Date getUploadTime() {
        return uploadTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_log.upload_time
     *
     * @param uploadTime the value for system_log.upload_time
     *
     * @mbg.generated
     */
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_log.platform
     *
     * @return the value of system_log.platform
     *
     * @mbg.generated
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_log.platform
     *
     * @param platform the value for system_log.platform
     *
     * @mbg.generated
     */
    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_log.type
     *
     * @return the value of system_log.type
     *
     * @mbg.generated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_log.type
     *
     * @param type the value for system_log.type
     *
     * @mbg.generated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_log.create_time
     *
     * @return the value of system_log.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_log.create_time
     *
     * @param createTime the value for system_log.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}