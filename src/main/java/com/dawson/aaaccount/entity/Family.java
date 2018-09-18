package com.dawson.aaaccount.entity;
 
import java.util.List;

import javax.persistence.Entity; 
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table; 

@Entity
@Table(name="family")
public class Family  extends BaseEntity {
	private static final long serialVersionUID = 7419229896231522702L;
     
    private String name;
 
    @ManyToOne
    @JoinColumn(name="creator_id")
    private User creator;
    
    @ManyToMany
    @JoinTable(name="family_member",joinColumns=@JoinColumn(name="family_id"),inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<User> member;

    public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<User> getMember() {
		return member;
	}

	public void setMember(List<User> member) {
		this.member = member;
	}
  
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}