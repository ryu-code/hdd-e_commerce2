package kr.hhplus.be.domain.order.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.hhplus.be.domain.order.entity.OrderDto;
import kr.hhplus.be.domain.order.entity.OrderResponse;
import kr.hhplus.be.domain.order.facade.OrderFacade;
import kr.hhplus.be.domain.order.service.OrderService;
import kr.hhplus.be.domain.product.entity.ProductDto;
import kr.hhplus.be.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderFacade orderFacade;

    @PutMapping("")
    public OrderResponse saveOrder(@RequestBody OrderDto orderDto, HttpServletRequest httpServletRequest) {

        return orderFacade.saveOrder(orderDto);
    }
}
