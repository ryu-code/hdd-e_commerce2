package kr.hhplus.be.server.domain.order.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.hhplus.be.server.domain.order.entity.OrderDto;
import kr.hhplus.be.server.domain.order.entity.OrderResponse;
import kr.hhplus.be.server.domain.order.facade.OrderFacade;
import kr.hhplus.be.server.domain.order.service.OrderService;
import kr.hhplus.be.server.domain.product.entity.ProductDto;
import kr.hhplus.be.server.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private final OrderFacade orderFacade;

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public OrderResponse saveOrder(@RequestBody OrderDto orderDto) throws Exception {

        return orderFacade.saveOrder(orderDto);
    }
}
