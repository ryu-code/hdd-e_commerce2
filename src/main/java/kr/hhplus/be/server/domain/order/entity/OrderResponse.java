package kr.hhplus.be.domain.order.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hhplus.be.domain.point.entity.PointDto;
import kr.hhplus.be.domain.product.entity.ProductDto;
import lombok.Builder;

@Builder
public class OrderResponse {

    @JsonProperty("point")
    private PointDto point;

    @JsonProperty("order")
    private Order order;

    @JsonProperty("product")
    private ProductDto product;
}
