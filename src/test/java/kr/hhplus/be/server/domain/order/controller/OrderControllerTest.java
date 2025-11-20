package kr.hhplus.be.server.domain.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.hhplus.be.global.error.ErrorException;
import kr.hhplus.be.server.domain.order.entity.Order;
import kr.hhplus.be.server.domain.order.entity.OrderDto;
import kr.hhplus.be.server.domain.order.entity.OrderResponse;
import kr.hhplus.be.server.domain.order.facade.OrderFacade;
import kr.hhplus.be.server.domain.point.entity.PointDto;
import kr.hhplus.be.server.domain.product.entity.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(OrderController.class)
@DisplayName("OrderController addPoint API Tests")
class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private OrderFacade orderFacade;

	// 주문 성공
	@Test
	@DisplayName("주문 성공")
	void testSaveOrderSuccess() throws Exception {
		Long userId = 1L;
		Long productId = 100L;
		int totalAmount = 2;
		int productPrice = 5000;
		int productStock = 10;

		OrderDto requestDto = OrderDto.builder()
				.userId(userId)
				.productId(productId)
				.totalAmount(totalAmount)
				.build();

		Order order = Order.builder()
				.orderId(1L)
				.userId(userId)
				.totalAmount(totalAmount)
				.orderStatus("COMPLETED")
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.build();

		ProductDto product = ProductDto.builder()
				.productId(productId)
				.productName("연필")
				.price(productPrice)
				.stock(productStock - totalAmount)  // 차감 후
				.createdAt(LocalDateTime.now())
				.build();

		PointDto point = PointDto.builder()
				.pointId(1L)
				.userId(userId)
				.point(15000L - (productPrice * totalAmount))  // 차감 후
				.reason("ORDER")
				.relatedOrderId(order.getOrderId())
				.createdAt(LocalDateTime.now())
				.build();

		OrderResponse response = OrderResponse.builder()
				.order(order)
				.product(product)
				.point(point)
				.build();

		when(orderFacade.saveOrder(any(OrderDto.class))).thenReturn(response);

		mockMvc.perform(put("/order")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(requestDto)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.order.orderId", equalTo(1)))
				.andExpect(jsonPath("$.order.userId", equalTo(userId.intValue())))
				.andExpect(jsonPath("$.product.stock", equalTo(8)))
				.andExpect(jsonPath("$.point.point", equalTo(5000)))
				.andExpect(jsonPath("$.point.reason", equalTo("ORDER")));
	}

	// 주문 실패 - 재고 부족
	@Test
	@DisplayName("주문 실패 - 재고 부족")
	void testSaveOrderFailStockInsufficient() throws Exception {
		Long userId = 1L;
		Long productId = 100L;
		int totalAmount = 15;
		int productStock = 10;

		OrderDto requestDto = OrderDto.builder()
				.userId(userId)
				.productId(productId)
				.totalAmount(totalAmount)
				.build();

		when(orderFacade.saveOrder(any(OrderDto.class)))
				.thenThrow(new ErrorException.CantNotPurchaseException("재고가 부족합니다."));

		mockMvc.perform(put("/order")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(requestDto)))
				.andExpect(status().isBadRequest());
	}

	// 주문 실패 - 포인트 부족
	@Test
	@DisplayName("주문 실패 - 포인트 부족")
	void testSaveOrderFailPointInsufficient() throws Exception {
		Long userId = 1L;
		Long productId = 100L;
		int totalAmount = 5;
		int productPrice = 5000;

		OrderDto requestDto = OrderDto.builder()
				.userId(userId)
				.productId(productId)
				.totalAmount(totalAmount)
				.build();

		when(orderFacade.saveOrder(any(OrderDto.class)))
				.thenThrow(new ErrorException.CantNotPurchaseException("포인트가 부족합니다."));

		mockMvc.perform(put("/order")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(requestDto)))
				.andExpect(status().isBadRequest());
	}
}