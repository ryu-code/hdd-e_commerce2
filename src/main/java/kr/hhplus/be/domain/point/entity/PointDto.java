package kr.hhplus.be.domain.point.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PointDto {

    Long pointId;

    Long userId;

    Long balance;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}
