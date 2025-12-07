package kr.hhplus.be.server.domain.point.facade;

import kr.hhplus.be.global.error.BusinessException;
import kr.hhplus.be.server.domain.point.entity.PointDto;
import kr.hhplus.be.server.domain.point.service.PointService;
import kr.hhplus.be.server.domain.pointHistory.entity.PointResponse;
import kr.hhplus.be.server.domain.pointHistory.service.PointHistoryService;
import kr.hhplus.be.global.error.ErrorException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class PointFacade {

    private final PointService pointService;
    private final PointHistoryService pointHistoryService;

    @SneakyThrows
    public PointResponse addPoint(PointDto pointDto) {
        if (pointDto.getUserId() == null) throw new BusinessException.InputDataNullException();
        if (pointDto.getAmount() < 0) throw new BusinessException.InvalidValueException();

        // 가지고있던 포인트 조회
        long prevPoint = pointService.checkUserPoint(pointDto.getUserId());
        pointDto.setPoint(prevPoint + pointDto.getAmount());
        pointService.save(pointDto);

        // 포인트 히스토리 등록
        pointDto.setReason("CHARGE");
        pointHistoryService.savePointHistory(pointDto);

        return PointResponse.builder()
                .userPoint(pointDto)
                .build();
    }
}
