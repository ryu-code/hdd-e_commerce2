package kr.hhplus.be.server.domain.order.service;

import kr.hhplus.be.server.domain.order.entity.Order;
import kr.hhplus.be.server.domain.order.entity.OrderDto;
import kr.hhplus.be.server.domain.order.repository.OrderRepository;
import kr.hhplus.be.global.error.ErrorException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @SneakyThrows
    public Order save(OrderDto orderDto) {
        if (orderDto.getProductId() == null || orderDto.getUserId() == null)
            throw ErrorException.inputDataNullException;

        return orderRepository.save(orderDto);
    }
}
