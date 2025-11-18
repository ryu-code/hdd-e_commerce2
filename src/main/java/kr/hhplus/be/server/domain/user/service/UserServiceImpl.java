package kr.hhplus.be.server.domain.user.service;

import kr.hhplus.be.server.domain.user.entity.UserDto;
import kr.hhplus.be.server.domain.user.repository.UserRepository;
import kr.hhplus.be.global.error.ErrorException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @SneakyThrows
    public UserDto getUser(Long userId) {
        if (userId == null) throw ErrorException.inputDataNullException;
        else if (userId < 1) throw ErrorException.invalidValueException ;

        return userRepository.findByUserId(userId);
    }
}
