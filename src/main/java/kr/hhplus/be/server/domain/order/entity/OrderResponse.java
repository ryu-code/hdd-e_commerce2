package kr.hhplus.be.server.domain.order.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderResponse {

    String message;
    String code;
}
