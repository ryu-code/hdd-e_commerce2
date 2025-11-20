package kr.hhplus.be.server.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

    Long userId;

    Long productId;

    String productName;

    int price;

    int stock;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}
