package kr.hhplus.be.server.domain.point.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PointDto {

    Long pointId;

    Long userId;

    Long point;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    String reason;

    Long relatedOrderId;

    Long amount;
}
