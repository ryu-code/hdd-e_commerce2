package kr.hhplus.be.domain.user.serviceImpl;

import kr.hhplus.be.domain.user.entity.UserDto;
import kr.hhplus.be.domain.user.repository.UserRepository;
import kr.hhplus.be.domain.user.service.UserService;
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

        return userRepository.findByUserId(userId);
    }
}
