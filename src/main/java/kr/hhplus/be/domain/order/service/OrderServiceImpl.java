package kr.hhplus.be.domain.order.service;

import kr.hhplus.be.domain.order.entity.Order;
import kr.hhplus.be.domain.order.entity.OrderDto;
import kr.hhplus.be.domain.order.repository.OrderRepository;
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
