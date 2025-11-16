package kr.hhplus.be.domain.point.service;

import kr.hhplus.be.domain.point.entity.PointDto;
import kr.hhplus.be.domain.point.repository.PointRepository;
import kr.hhplus.be.global.error.ErrorException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;


@RequiredArgsConstructor
public class PointServiceImpl implements PointService{

    private final PointRepository pointRepository;

    @SneakyThrows
    public PointDto getUserPoint(Long userId) {
        if (userId == null) throw ErrorException.inputDataNullException;

        return pointRepository.findPointByUserId(userId);
    }

}
