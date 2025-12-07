package kr.hhplus.be.server.domain.user.facade;

import kr.hhplus.be.server.domain.point.entity.PointDto;
import kr.hhplus.be.server.domain.point.service.PointService;
import kr.hhplus.be.server.domain.user.entity.UserDto;
import kr.hhplus.be.server.domain.user.entity.UserResponse;
import kr.hhplus.be.server.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final PointService pointService;

    public UserResponse getUser(Long userId) {

        //사용자 기본 정보 조회
        UserDto userResponse = userService.getUser(userId);

        //사용자 포인트 조회
        long pointResponse = pointService.checkUserPoint(userId);

        return UserResponse.builder()
                .user(userResponse)
                .userPoint(pointResponse)
                .build();
    }
}
