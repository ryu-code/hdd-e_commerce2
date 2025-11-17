package kr.hhplus.be.domain.point.facade;

import kr.hhplus.be.domain.point.entity.PointDto;
import kr.hhplus.be.domain.point.service.PointService;
import kr.hhplus.be.domain.pointHistory.entity.PointResponse;
import kr.hhplus.be.domain.pointHistory.service.PointHistoryService;
import kr.hhplus.be.domain.user.service.UserService;
import kr.hhplus.be.global.error.ErrorException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class PointFacade {

    private final UserService userService;
    private final PointService pointService;
    private final PointHistoryService pointHistoryService;

    @SneakyThrows
    public PointResponse addPoint(PointDto pointDto) {
        if (pointDto.getUserId() == null) throw ErrorException.inputDataNullException;
        if (pointDto.getAmount() < 0) throw new ErrorException.CantNotChargeException("충전 금액은 0원보다 적을 수 없습니다.");

        // 가지고있던 포인트 조회
        PointDto prevPoint = pointService.getUserPoint(pointDto.getUserId());
        prevPoint.setPoint(prevPoint.getPoint() + pointDto.getAmount());
        pointService.save(prevPoint);

        // 포인트 히스토리 등록
        prevPoint.setReason("CHARGE");
        pointHistoryService.savePointHistory(prevPoint);

        return PointResponse.builder()
                .userPoint(prevPoint)
                .build();
    }
}
