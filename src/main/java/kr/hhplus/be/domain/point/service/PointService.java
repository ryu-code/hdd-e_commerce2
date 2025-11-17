package kr.hhplus.be.domain.point.service;

import kr.hhplus.be.domain.point.entity.PointDto;
import kr.hhplus.be.domain.product.entity.Product;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public interface PointService {

    PointDto getUserPoint(Long userId);

    Point save(PointDto pointDto);
}
