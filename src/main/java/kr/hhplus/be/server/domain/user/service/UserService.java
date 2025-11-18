package kr.hhplus.be.domain.user.service;

import kr.hhplus.be.domain.user.entity.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDto getUser(Long userId);
}
