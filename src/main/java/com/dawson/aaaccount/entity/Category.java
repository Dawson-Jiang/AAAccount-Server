package com.dawson.aaaccount.entity;
 
import javax.persistence.Entity; 
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category extends BaseEntity{ 
    private static final long serialVersionUID = 7419229779739851545L;
    
     private String name;
 
    private Integer orderFlag;

          public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
 
    public Integer getOrderFlag() {
        return orderFlag;
    }
 
    public void setOrderFlag(Integer orderFlag) {
        this.orderFlag = orderFlag;
    } 
}