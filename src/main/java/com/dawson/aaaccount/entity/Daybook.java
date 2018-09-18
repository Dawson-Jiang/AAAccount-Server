package com.dawson.aaaccount.entity;
 
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table; 

@Entity
@Table(name="daybook")
public class Daybook extends BaseEntity {
	private static final long serialVersionUID = 7419229896237854702L;
  
    private BigDecimal money;
 
    @ManyToOne
    @JoinColumn(name = "recorder_id")
    private User recorder;
 
    private String des;
 
    private Date date;
 
    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;
 
    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payer;
 
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    @ManyToMany
    @JoinTable(name="daybook_consumer",joinColumns=@JoinColumn(name="daybook_id"),inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<User> consumer;

    public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	public User getPayer() {
		return payer;
	}

	public void setPayer(User payer) {
		this.payer = payer;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<User> getConsumer() {
		return consumer;
	}

	public void setConsumer(List<User> consumer) {
		this.consumer = consumer;
	}

	public Settle getSettle() {
		return settle;
	}

	public void setSettle(Settle settle) {
		this.settle = settle;
	}

	public void setRecorder(User recorder) {
		this.recorder = recorder;
	}
 
    @ManyToOne
    @JoinColumn(name = "settle_id")
    private Settle settle;
 
    private String pic1;
 
    private String pic2;
 
    private String pic3;

    public BigDecimal getMoney() {
        return money;
    }
 
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
 
    public String getDes() {
        return des;
    }
 
    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }
 
    public Date getDate() {
        return date;
    }
 
    public void setDate(Date date) {
        this.date = date;
    }
 
    public String getPic1() {
        return pic1;
    }
 
    public void setPic1(String pic1) {
        this.pic1 = pic1 == null ? null : pic1.trim();
    }
 
    public String getPic2() {
        return pic2;
    }
 
    public void setPic2(String pic2) {
        this.pic2 = pic2 == null ? null : pic2.trim();
    } 
    
    public String getPic3() {
        return pic3;
    }
 
    public void setPic3(String pic3) {
        this.pic3 = pic3 == null ? null : pic3.trim();
    }
}