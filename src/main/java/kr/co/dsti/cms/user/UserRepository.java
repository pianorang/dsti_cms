package kr.co.dsti.cms.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT a FROM User a WHERE userAccount =:username")
    @Transactional(readOnly = true)
    User findByAccount(@Param("username") String username);
}
