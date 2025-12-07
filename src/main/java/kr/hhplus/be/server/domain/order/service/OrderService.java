package kr.hhplus.be.server.domain.order.service;

import kr.hhplus.be.server.domain.order.entity.Order;
import kr.hhplus.be.server.domain.order.entity.OrderDto;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

//    Order save(OrderDto orderDto);

    void saveOrderExecute(OrderDto orderDto);
}
