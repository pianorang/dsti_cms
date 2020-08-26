package kr.co.dsti.cms.user;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import kr.co.dsti.cms.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TN_USERS")
@Getter @Setter
public class User extends BaseEntity {
	@Column(name="user_name", length=50)	
	private String userName;
	
	@Column(name="user_account", length=100)
	private String userAccount;
	
	@Column(name="user_password", length=255)
	private String userPassword;
	
	@Column(name="role_name", length=50)
	private String roleName;
	
}
