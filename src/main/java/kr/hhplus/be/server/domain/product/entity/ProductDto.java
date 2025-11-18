package kr.hhplus.be.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
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
