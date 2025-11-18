package kr.hhplus.be.server.domain.point.service;

import kr.hhplus.be.server.domain.point.entity.PointDto;
import kr.hhplus.be.server.domain.pointHistory.entity.PointResponse;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public interface PointService {

    PointDto getUserPoint(Long userId);

    Point save(PointDto pointDto);
}
