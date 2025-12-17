package kr.hhplus.be.server.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hhplus.be.server.domain.point.entity.PointDto;
import kr.hhplus.be.server.domain.user.entity.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse {

    @JsonProperty("product")
    private ProductDto product;

    @JsonProperty("userPoint")
    private PointDto userPoint;
}