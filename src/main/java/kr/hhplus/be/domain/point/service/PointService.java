package kr.hhplus.be.domain.point.service;

import kr.hhplus.be.domain.point.entity.PointDto;
import org.springframework.stereotype.Service;

@Service
public interface PointService {

    PointDto getUserPoint(Long userId);
}
