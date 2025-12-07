package kr.hhplus.be.server.domain.pointHistory.entity;

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

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}
