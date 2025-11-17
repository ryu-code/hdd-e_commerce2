package kr.hhplus.be.domain.pointHistory.repository;

import kr.hhplus.be.domain.pointHistory.entity.PointHistory;
import kr.hhplus.be.domain.pointHistory.entity.PointHistoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {

    PointHistory save(PointHistoryDto pointHistoryDto);
}
