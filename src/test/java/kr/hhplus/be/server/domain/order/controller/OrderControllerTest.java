package kr.hhplus.be.server.domain.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.hhplus.be.server.domain.order.entity.OrderDto;
import kr.hhplus.be.server.domain.order.entity.OrderResponse;
import kr.hhplus.be.server.domain.order.facade.OrderFacade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@DisplayName("OrderController 주문 API Tests")
class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private OrderFacade orderFacade;

	@Test
	@DisplayName("주문 생성 성공")
	void createOrder_success() throws Exception {
		// given
		OrderDto request = OrderDto.builder()
				.userId(1L)
				.productId(1L)
				.orderStatus("ORDER")
				.totalAmount(5)
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.build();

		OrderResponse response = OrderResponse.builder()
				.message("SUCCESS")
				.code("200")
				.build();

		when(orderFacade.saveOrder(any(OrderDto.class)))
				.thenReturn(response);

		// when & then
		mockMvc.perform(
						put("/order")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(request))
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.code").value("200"));
	}

	@Test
	@DisplayName("주문 실패 - 재고 부족")
	void createOrder_fail_outOfStock() throws Exception {

		OrderDto request = OrderDto.builder()
				.userId(1L)
				.productId(99L)
				.orderStatus("ORDER")
				.totalAmount(100)
				.build();

		when(orderFacade.saveOrder(any(OrderDto.class)))
				.thenThrow(new IllegalStateException("재고 부족"));

		mockMvc.perform(
						put("/order")
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(request))
				)
				.andExpect(status().isInternalServerError());
	}

	@Test
	@DisplayName("주문 실패 - 포인트 부족")
	void createOrder_fail_notEnoughPoint() throws Exception {

		OrderDto request = OrderDto.builder()
				.userId(1L)
				.productId(1L)
				.orderStatus("ORDER")
				.totalAmount(999999)
				.build();

		when(orderFacade.saveOrder(any(OrderDto.class)))
				.thenThrow(new IllegalArgumentException("포인트 부족"));

		mockMvc.perform(
						put("/order")
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(request))
				)
				.andExpect(status().isBadRequest());
	}


	@Test
	@DisplayName("주문 실패 - 잘못된 JSON")
	void createOrder_fail_invalidJson() throws Exception {

		String brokenJson = "{ this is not valid json }";

		mockMvc.perform(
						put("/order")
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(brokenJson)
				)
				.andExpect(status().isBadRequest());
	}


	@Test
	@DisplayName("주문 실패 - Content-Type 미지원")
	void createOrder_fail_invalidContentType() throws Exception {

		OrderDto request = OrderDto.builder()
				.userId(1L)
				.productId(1L)
				.orderStatus("ORDER")
				.totalAmount(5)
				.build();

		mockMvc.perform(
						put("/order")
								.contentType(MediaType.TEXT_PLAIN)   // ❌ JSON 아님
								.accept(MediaType.APPLICATION_JSON)
								.content("just text")
				)
				.andExpect(status().isUnsupportedMediaType());
	}

	@Test
	@DisplayName("주문 실패 - RequestBody 없음")
	void createOrder_fail_emptyBody() throws Exception {

		mockMvc.perform(
						put("/order")
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("주문 실패 - userId 누락")
	void createOrder_fail_missingUserId() throws Exception {

		OrderDto request = OrderDto.builder()
				// userId 없음
				.productId(1L)
				.orderStatus("ORDER")
				.totalAmount(5)
				.build();

		mockMvc.perform(
						put("/order")
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(request))
				)
				.andExpect(status().isBadRequest());
	}


}
