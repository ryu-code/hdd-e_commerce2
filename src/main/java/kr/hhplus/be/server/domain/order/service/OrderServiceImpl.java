package kr.hhplus.be.server.domain.order.service;

import kr.hhplus.be.global.error.AppSystemException;
import kr.hhplus.be.global.error.BusinessException;
import kr.hhplus.be.server.domain.order.entity.Order;
import kr.hhplus.be.server.domain.order.entity.OrderDto;
import kr.hhplus.be.server.domain.order.repository.OrderRepository;
import kr.hhplus.be.global.error.ErrorException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public long saveOrderExecute(OrderDto orderDto) {
        try {
            if (orderDto.getUserId() == null || orderDto.getProductId() == null) throw new BusinessException.InputDataNullException();
            if (orderDto.getTotalAmount() < 1) throw new BusinessException.InvalidValueException();

            Order order = new Order();
            order.setUserId(orderDto.getUserId());
            order.setProductId(orderDto.getProductId());
            order.setOrderStatus(orderDto.getOrderStatus());
            order.setTotalAmount(orderDto.getTotalAmount());

            Order orderSave = orderRepository.save(order);

            return orderSave.getOrderId();
        } catch (Exception e) {
            throw new AppSystemException("주문 저장 중 시스템 오류가 발생했습니다.", e);
        }

    }
}
