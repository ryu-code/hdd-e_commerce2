package kr.hhplus.be.server.domain.point.service;

import kr.hhplus.be.server.domain.point.entity.PointDto;
import kr.hhplus.be.server.domain.point.repository.PointRepository;
import kr.hhplus.be.server.domain.pointHistory.entity.PointResponse;
import kr.hhplus.be.global.error.ErrorException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.awt.*;


@RequiredArgsConstructor
public class PointServiceImpl implements PointService{

    private final PointRepository pointRepository;

    @SneakyThrows
    public PointDto getUserPoint(Long userId) {
        if (userId == null) throw ErrorException.inputDataNullException;

        return pointRepository.findPointByUserId(userId);
    }

    @SneakyThrows
    public Point save(PointDto pointDto) {
        return pointRepository.save(pointDto);
    }
}
