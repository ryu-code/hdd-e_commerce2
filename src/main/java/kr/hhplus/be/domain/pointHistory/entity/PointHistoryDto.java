package kr.hhplus.be.domain.pointHistory.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PointHistoryDto {

    Long pointHistoryId;

    Long pointId;

    Long point;

    String reason;

    Long relatedOrderId;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}
