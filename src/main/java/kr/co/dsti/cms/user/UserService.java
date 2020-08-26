package kr.co.dsti.cms.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.dsti.cms.model.DataTable;

@Service
public class UserService implements UserDetailsService {

	
	
	@Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByAccount(username);
		
		if (user == null){
            throw new UsernameNotFoundException(username);
        }
		
		UserDetails userDetails = new UserDetails() {

			@Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                return authorities;
            }

            @Override
            public String getPassword() {
                return user.getUserPassword();
            }

            @Override
            public String getUsername() {
                return user.getUserAccount();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
			
		};		
		
		return userDetails;
	}
	
	public User save(User user){
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return userRepository.save(user);
    }

    public User findById(Long id){
        return userRepository.getOne(id);
    }
    
    public DataTable getList(Pageable pageable, Integer draw){
        Page<User> list = userRepository.findAll(pageable);
        DataTable dt = new DataTable();
        dt.setDraw(draw);
        dt.setData(list.getContent());
        dt.setRecordsFiltered(list.getTotalElements());
        dt.setRecordsTotal(list.getTotalElements());

        return dt;
    }

}
