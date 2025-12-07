package kr.hhplus.be.server.domain.point.service;

import kr.hhplus.be.server.domain.point.entity.PointDto;
import kr.hhplus.be.server.domain.pointHistory.entity.PointResponse;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public interface PointService {

    long checkUserPoint(Long userId);

    Point save(PointDto pointDto);

    void purchaseExecute(Long userId, Long price, Long userPoint);
}
