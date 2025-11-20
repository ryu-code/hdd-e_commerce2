package kr.hhplus.be.server.domain.product.controller;

import kr.hhplus.be.server.domain.point.entity.PointDto;
import kr.hhplus.be.server.domain.product.entity.ProductDto;
import kr.hhplus.be.server.domain.product.service.ProductService;
import kr.hhplus.be.server.domain.user.entity.UserDto;
import kr.hhplus.be.server.domain.user.entity.UserResponse;
import kr.hhplus.be.server.domain.user.facade.UserFacade;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(ProductController.class)
@DisplayName("UserController getUser API Tests")
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;


	@Test
	@DisplayName("상품 조회 성공")
	void testGetProductSuccess() throws Exception {
		Long productId = 1L;

		ProductDto response = ProductDto.builder()
				.productId(productId)
				.productName("상품명")
				.price(5000)
				.stock(100)
				.createdAt(LocalDateTime.now())
				.build();

		when(productService.getProduct(productId)).thenReturn(response);

		mockMvc.perform(get("/product/{productId}", productId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.productId", equalTo(productId.intValue())))
				.andExpect(jsonPath("$.productName", equalTo("상품명")))
				.andExpect(jsonPath("$.price", equalTo(5000)));
	}

	@Test
	@DisplayName("존재하지 않는 상품")
	void testGetProductNotFound() throws Exception {
		Long productId = 999L;

		when(productService.getProduct(productId))
				.thenThrow(new IllegalArgumentException("Product not found"));

		mockMvc.perform(get("/product/{productId}", productId))
				.andExpect(status().is4xxClientError());

		verify(productService, times(1)).getProduct(productId);
	}

	@Test
	@DisplayName("존재하지 않는 상품")
	void testInvalidNegativeProductId() throws Exception {
		Long productId = -1L;

		when(productService.getProduct(productId))
				.thenThrow(new IllegalArgumentException("Invalid productId"));

		mockMvc.perform(get("/product/{productId}", productId))
				.andExpect(status().is4xxClientError());
	}

	@Test
	@DisplayName("productId = 0 → 4xx")
	void testZeroProductId() throws Exception {
		Long productId = 0L;

		when(productService.getProduct(productId))
				.thenThrow(new IllegalArgumentException("Invalid userId"));

		mockMvc.perform(get("/product/{productId}", productId))
				.andExpect(status().is4xxClientError());
	}

	@Test
	@DisplayName("응답 구조 검증")
	void testResponseStructure() throws Exception {
		Long productId = 1L;

		ProductDto response = ProductDto.builder()
				.productId(productId)
				.productName("연필")
				.price(5000)
				.stock(100)
				.createdAt(LocalDateTime.now())
				.build();

		when(productService.getProduct(productId)).thenReturn(response);

		mockMvc.perform(get("/product/{productId}", productId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").value(productId.intValue()))
				.andExpect(jsonPath("$.productName").value("연필"))
				.andExpect(jsonPath("$.price").value(5000))
				.andExpect(jsonPath("$.stock").value(100));
	}

	@Test
	@DisplayName("Content-Type JSON 정상 반환")
	void testContentType() throws Exception {
		Long productId = 1L;

		ProductDto response = ProductDto.builder()
				.productId(productId)
				.productName("연필")
				.price(5000)
				.stock(100)
				.createdAt(LocalDateTime.now())
				.build();

		when(productService.getProduct(productId)).thenReturn(response);

		mockMvc.perform(get("/product/{productId}", productId))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	@DisplayName("Facade 호출 검증: 1회 호출")
	void testFacadeCall() throws Exception {
		Long productId = 5L;

		ProductDto response = ProductDto.builder()
				.productId(productId)
				.productName("연필")
				.price(5000)
				.stock(100)
				.createdAt(LocalDateTime.now())
				.build();

		when(productService.getProduct(productId)).thenReturn(response);

		mockMvc.perform(get("/product/{productId}", productId))
				.andExpect(status().isOk());

		verify(productService, times(1)).getProduct(productId);
	}

	@Test
	@DisplayName("매우 큰 productId 테스트")
	void testLargeUserId() throws Exception {
		Long productId = Long.MAX_VALUE - 1;

		ProductDto response = ProductDto.builder()
				.productId(productId)
				.productName("연필")
				.price(5000)
				.stock(100)
				.createdAt(LocalDateTime.now())
				.build();

		when(productService.getProduct(productId)).thenReturn(response);

		mockMvc.perform(get("/product/{productId}", productId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").value(productId.intValue()));
	}
}
