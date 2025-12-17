package kr.hhplus.be.server.domain.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {

    Long orderId;

    Long userId;

    Long productId;

    String orderStatus;

    int totalAmount;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}
