package kr.hhplus.be.server.domain.user.service;

import kr.hhplus.be.server.domain.user.entity.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDto getUser(Long userId);
}
