package kr.hhplus.be.domain.pointHistory.service;

import kr.hhplus.be.domain.point.entity.PointDto;
import kr.hhplus.be.domain.pointHistory.entity.PointHistory;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public interface PointHistoryService {

    PointHistory savePointHistory(PointDto pointDto);
}
