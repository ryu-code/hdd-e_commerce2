package kr.hhplus.be.server.domain.order.facade;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.domain.order.entity.OrderDto;
import kr.hhplus.be.server.domain.order.entity.OrderResponse;
import kr.hhplus.be.server.domain.order.service.OrderService;
import kr.hhplus.be.server.domain.point.service.PointService;
import kr.hhplus.be.server.domain.product.service.ProductService;
import kr.hhplus.be.server.util.event.CompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class OrderFacade {

    private final PointService pointService;
    private final ProductService productService;
    private final OrderService orderService;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public OrderResponse saveOrder(OrderDto orderDto) throws Exception {

        // 사용자 포인트 조회
        long userPoint = pointService.checkUserPoint(orderDto.getUserId());

        // 상품아이템 확인
        long productPrice = productService.productPrice(orderDto.getProductId());
        productService.checkProduct(userPoint, orderDto.getProductId(), orderDto.getTotalAmount());

        // 주문 이력 저장
        long orderId = orderService.saveOrderExecute(orderDto);

        // 사용자 포인트 차감
        pointService.purchaseExecute(orderDto.getUserId(), productPrice, userPoint);

        eventPublisher.publishEvent(
                new CompletedEvent(orderId, orderDto.getUserId(), orderDto.getProductId(), orderDto.getTotalAmount())
        );

        return OrderResponse.builder()
                .message("주문에 성공하셨습니다.")
                .code("200")
                .build();
    }
}
