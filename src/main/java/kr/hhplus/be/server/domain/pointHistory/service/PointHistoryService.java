package kr.hhplus.be.server.domain.pointHistory.service;

import kr.hhplus.be.server.domain.point.entity.PointDto;
import kr.hhplus.be.server.domain.pointHistory.entity.PointHistory;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public interface PointHistoryService {

    PointHistory savePointHistory(PointDto pointDto);
}
