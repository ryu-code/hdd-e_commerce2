package kr.hhplus.be.domain.order.facade;

import kr.hhplus.be.domain.order.entity.Order;
import kr.hhplus.be.domain.order.entity.OrderDto;
import kr.hhplus.be.domain.order.entity.OrderResponse;
import kr.hhplus.be.domain.order.service.OrderService;
import kr.hhplus.be.domain.point.entity.PointDto;
import kr.hhplus.be.domain.point.service.PointService;
import kr.hhplus.be.domain.pointHistory.service.PointHistoryService;
import kr.hhplus.be.domain.product.entity.ProductDto;
import kr.hhplus.be.domain.product.service.ProductService;
import kr.hhplus.be.global.error.ErrorException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class OrderFacade {

    private final PointService pointService;
    private final ProductService productService;
    private final OrderService orderService;
    private final PointHistoryService pointHistoryService;

    @SneakyThrows
    public OrderResponse saveOrder(OrderDto orderDto) {

        // 주문 이력 저장
        Order order = orderService.save(orderDto);

        // 상품아이템 조회
        ProductDto product = productService.getProduct(orderDto.getProductId());

        if (product.getStock() < orderDto.getTotalAmount())
            throw new ErrorException.CantNotPurchaseException("재고가 부족합니다.");

        // 사용자 포인트 조회
        PointDto userPoint = pointService.getUserPoint(orderDto.getUserId());
        if (userPoint.getBalance() < product.getPrice() * orderDto.getTotalAmount())
            throw new ErrorException.CantNotPurchaseException("포인트가 부족합니다.");

        // 상품 구매
        product.setStock(product.getStock() - orderDto.getTotalAmount());
        productService.save(product);

        // 사용자 포인트 차감
        userPoint.setBalance(userPoint.getBalance() - (product.getPrice() * orderDto.getTotalAmount()));
        pointService.save(userPoint);

        // 사용자 포인트 히스토리 저장
        userPoint.setReason("ORDER");
        userPoint.setRelatedOrderId(order.getOrderId());
        pointHistoryService.savePointHistory(userPoint);

        return OrderResponse.builder()
                .point(userPoint)
                .order(order)
                .product(product)
                .build();
    }
}
