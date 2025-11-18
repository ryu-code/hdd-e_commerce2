package kr.hhplus.be.domain.order.service;

import kr.hhplus.be.domain.order.entity.Order;
import kr.hhplus.be.domain.order.entity.OrderDto;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    Order save(OrderDto orderDto);
}
