package kr.hhplus.be.domain.user.repository;

import kr.hhplus.be.domain.user.entity.User;
import kr.hhplus.be.domain.user.entity.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDto findByUserId(Long userId);
}
