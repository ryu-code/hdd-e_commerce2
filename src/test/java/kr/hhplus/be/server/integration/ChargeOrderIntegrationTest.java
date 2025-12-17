package kr.hhplus.be.server.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.hhplus.be.server.domain.order.controller.OrderController;
import kr.hhplus.be.server.domain.order.entity.OrderDto;
import kr.hhplus.be.server.domain.order.entity.OrderResponse;
import kr.hhplus.be.server.domain.order.facade.OrderFacade;
import kr.hhplus.be.server.domain.point.controller.PointController;
import kr.hhplus.be.server.domain.point.entity.PointDto;
import kr.hhplus.be.server.domain.point.facade.PointFacade;
import kr.hhplus.be.server.domain.pointHistory.entity.PointResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({OrderController.class, PointController.class})
@DisplayName("Charge → Order Controller Flow Test")
class ChargeOrderFlowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderFacade orderFacade;

    @MockBean
    private PointFacade pointFacade;

    @Test
    @DisplayName("포인트 충전 → 주문 → 흐름 성공 (DB 미사용)")
    void charge_then_order_flow_success() throws Exception {

        PointDto pointDto = PointDto.builder()
                .userId(1L)
                .point(10000L)
                .build();

        PointResponse pointResponse = PointResponse.builder()
                .userPoint(pointDto)
                .build();

        when(pointFacade.addPoint(any(PointDto.class)))
                .thenReturn(pointResponse);

        mockMvc.perform(
                        put("/point")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                    { "userId": 1, "amount": 10000 }
                                """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userPoint.userId").value(1))
                .andExpect(jsonPath("$.userPoint.point").value(10000));

        OrderResponse orderResponse = OrderResponse.builder()
                .message("ORDER_SUCCESS")
                .code("200")
                .build();

        when(orderFacade.saveOrder(any(OrderDto.class)))
                .thenReturn(orderResponse);

        mockMvc.perform(
                        put("/order")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                    { 
                                      "userId": 1, 
                                      "productId": 1, 
                                      "totalAmount": 3000, 
                                      "orderStatus": "ORDER" 
                                    }
                                """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("ORDER_SUCCESS"))
                .andExpect(jsonPath("$.code").value("200"));
    }
}

