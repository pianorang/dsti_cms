package kr.co.dsti.cms.user;
import javax.persistence.Entity;
import javax.persistence.Table;

import kr.co.dsti.cms.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TN_USERS")
@Getter @Setter
public class User extends BaseEntity {
	private String userName;
	private String userAccount;
	private String userPassword;
	private String roleName;
	
}
