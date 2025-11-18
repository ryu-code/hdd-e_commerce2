package kr.hhplus.be.server.domain.pointHistory.service;

import kr.hhplus.be.server.domain.point.entity.PointDto;
import kr.hhplus.be.server.domain.pointHistory.entity.PointHistory;
import kr.hhplus.be.server.domain.pointHistory.entity.PointHistoryDto;
import kr.hhplus.be.server.domain.pointHistory.repository.PointHistoryRepository;
import kr.hhplus.be.global.error.ErrorException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class PointHistoryServiceImpl implements PointHistoryService{

    private final PointHistoryRepository pointHistoryRepository;

    @SneakyThrows
    public PointHistory savePointHistory(PointDto pointDto) {
        if (pointDto.getUserId() == null) throw ErrorException.inputDataNullException;

        PointHistoryDto pointHistoryDto = new PointHistoryDto();
        pointHistoryDto.setPoint(pointDto.getPoint());
        pointHistoryDto.setPointId(pointDto.getPointId());
        pointHistoryDto.setReason(pointDto.getReason());
        pointHistoryDto.setRelatedOrderId(pointDto.getRelatedOrderId());

        return pointHistoryRepository.save(pointHistoryDto);
    }
}
