package kr.hhplus.be.server.domain.point.service;

import kr.hhplus.be.global.error.BusinessException;
import kr.hhplus.be.server.domain.point.entity.PointDto;
import kr.hhplus.be.server.domain.point.repository.PointRepository;
import kr.hhplus.be.server.domain.pointHistory.entity.PointHistory;
import kr.hhplus.be.server.domain.pointHistory.entity.PointHistoryDto;
import kr.hhplus.be.server.domain.pointHistory.repository.PointHistoryRepository;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.time.LocalDateTime;


@RequiredArgsConstructor
public class PointServiceImpl implements PointService{

    private final PointRepository pointRepository;
    private final PointHistoryRepository pointHistoryRepository;

    public long checkUserPoint(Long userId) {
        if (userId == null) throw new BusinessException.InputDataNullException();

        return pointRepository.findPointByUserId(userId).getPoint();
    }

    public Point save(PointDto pointDto) {
        return pointRepository.save(pointDto);
    }

    private PointHistory savePointHistory(PointDto pointDto) {
        if (pointDto.getUserId() == null) throw new BusinessException.InputDataNullException();

        PointHistoryDto pointHistoryDto = new PointHistoryDto();
        pointHistoryDto.setPoint(pointDto.getPoint());
        pointHistoryDto.setPointId(pointDto.getPointId());
        pointHistoryDto.setReason(pointDto.getReason());

        return pointHistoryRepository.save(pointHistoryDto);
    }

    public void purchaseExecute(Long userId, Long price, Long userPoint) {
        if (price < 0) throw new BusinessException.InvalidValueException();
        if (userId == null) throw new BusinessException.InputDataNullException();

        PointDto pointDto = PointDto.builder()
                .userId(userId)
                .point(userPoint - price)
                .reason("ORDER")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();


        save(pointDto);

        savePointHistory(pointDto);
    }
}
