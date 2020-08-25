package kr.co.dsti.cms.auth;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.dsti.cms.user.User;
import kr.co.dsti.cms.user.UserRepository;
import kr.co.dsti.cms.user.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userService;
	
	@GetMapping("login")
	public String login() {
		return "auth/login";
	}
	
	@PreAuthorize("permitAll()")
	@GetMapping("init")
	public String init() {		
		User user = userService.findByAccount("admin");
		if(user == null) {
			User adminUser = new User();
			adminUser.setUserAccount("admin");
			adminUser.setUserPassword(passwordEncoder.encode("dsti123!"));
			adminUser.setRoleName("ROLE_ADMIN");
			adminUser.setUserName("관리자");
			adminUser.setCreateDate(LocalDateTime.now());
			adminUser.setDelete(false);
			
			userService.save(adminUser);
					
		}		
		
		return "auth/login";
	}

}
 