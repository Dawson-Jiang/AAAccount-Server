package com.dawson.aaaccount.entity;
 
import javax.persistence.Entity; 
import javax.persistence.Table; 

@Entity
@Table(name = "login_info")
public class LoginInfo   extends BaseEntity{
	private static final long serialVersionUID = 7419265249731525896L;
    
    private String version;
 
    private String platform;
 
    private String phone;
 
    private String phoneType;
 
    private String os;
 
    public String getVersion() {
        return version;
    }
 
    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getPlatform() {
        return platform;
    }
 
    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }
 
    public String getPhone() {
        return phone;
    }
 
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
 
    public String getPhoneType() {
        return phoneType;
    }
 
    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType == null ? null : phoneType.trim();
    }

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	} 
	
	@Override
	public LoginInfo cleanClone() {
 		return null;
	}
}