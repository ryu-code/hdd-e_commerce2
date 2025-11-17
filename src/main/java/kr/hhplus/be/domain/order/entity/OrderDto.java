package kr.hhplus.be.domain.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
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
