package kr.hhplus.be.domain.pointHistory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Entity
public class PointHistory {

    @Id
    Long pointHistoryId;

    Long pointId;

    Long point;

    String reason;

    Long relatedOrderId;

    @CreatedDate
    LocalDateTime createdAt;

    @LastModifiedDate
    LocalDateTime updatedAt;
}
