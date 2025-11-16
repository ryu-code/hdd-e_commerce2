package kr.hhplus.be.domain.point.repository;

import kr.hhplus.be.domain.point.entity.PointDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface PointRepository extends JpaRepository<Point, Long> {

    PointDto findPointByUserId(Long userId);
}
