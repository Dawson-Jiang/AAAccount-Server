package com.dawson.aaaccount.entity;
 
import javax.persistence.Entity; 
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback  extends BaseEntity {
	private static final long serialVersionUID = 7419229779731968796L;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
 
    private String title;
 
    private String content;
 
    private String sessionId;
 
    private Byte status;
  
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
 
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
 
    public String getContent() {
        return content;
    }
 
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
 
    public String getSessionId() {
        return sessionId;
    }
 
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }
 
    public Byte getStatus() {
        return status;
    }
 
    public void setStatus(Byte status) {
        this.status = status;
    }
}