package kr.hhplus.be.domain.user.facade;

import kr.hhplus.be.domain.point.entity.PointDto;
import kr.hhplus.be.domain.point.service.PointService;
import kr.hhplus.be.domain.user.entity.UserDto;
import kr.hhplus.be.domain.user.entity.UserResponse;
import kr.hhplus.be.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final PointService pointService;

    public UserResponse getUser(Long userId) {

        //사용자 기본 정보 조회
        UserDto userResponse = userService.getUser(userId);

        //사용자 포인트 조회
        PointDto pointResponse = pointService.getUserPoint(userId);

        return UserResponse.builder()
                .user(userResponse)
                .userPoint(pointResponse)
                .build();
    }
}
