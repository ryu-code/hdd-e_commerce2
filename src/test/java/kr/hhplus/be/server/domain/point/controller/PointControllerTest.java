package kr.hhplus.be.server.domain.point.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.hhplus.be.global.error.BusinessException;
import kr.hhplus.be.global.error.ErrorException;
import kr.hhplus.be.server.domain.point.entity.PointDto;
import kr.hhplus.be.server.domain.point.facade.PointFacade;
import kr.hhplus.be.server.domain.pointHistory.entity.PointResponse;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(PointController.class)
@DisplayName("PointController addPoint API Tests")
class PointControllerTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PointFacade pointFacade;

	@Test
	@DisplayName("포인트 충전 성공")
	void testAddPointSuccess() throws Exception {
		Long userId = 1L;
		Long amount = 10000L;

		PointDto requestDto = PointDto.builder()
				.userId(userId)
				.amount(amount)
				.build();

		PointDto responsePointDto = PointDto.builder()
				.pointId(1L)
				.userId(userId)
				.point(10000L)
				.reason("CHARGE")
				.createdAt(LocalDateTime.now())
				.build();

		PointResponse response = PointResponse.builder()
				.userPoint(responsePointDto)
				.build();

		when(pointFacade.addPoint(any(PointDto.class))).thenReturn(response);

		mockMvc.perform(put("/point")  // HTTP 메서드 확인
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(requestDto)))  // body에 들어가야 함
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.userPoint.userId", equalTo(userId.intValue())))
				.andExpect(jsonPath("$.userPoint.point", equalTo(10000)));
	}

	@Test
	@DisplayName("포인트 충전 실패 - userId null")
	void testAddPointFailureUserIdNull() throws Exception {
		Long userId = null;
		Long amount = 10000L;

		PointDto requestDto = PointDto.builder()
				.userId(userId)
				.amount(amount)
				.build();

		PointDto responsePointDto = PointDto.builder()
				.pointId(1L)
				.userId(userId)
				.point(10000L)
				.reason("CHARGE")
				.createdAt(LocalDateTime.now())
				.build();

		PointResponse response = PointResponse.builder()
				.userPoint(responsePointDto)
				.build();

		when(pointFacade.addPoint(any(PointDto.class))).thenReturn(response);

		mockMvc.perform(put("/point")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(requestDto)))
				.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("포인트 충전 실패 - amount 음수")
	void testAddPointFailureNegativeAmount() throws Exception {
		Long userId = 1L;

		PointDto requestDto = PointDto.builder()
				.userId(userId)
				.amount(-1000L)  // 음수
				.build();

		when(pointFacade.addPoint(any(PointDto.class)))
				.thenThrow(new BusinessException.CantNotChargeException("충전 금액은 0원보다 적을 수 없습니다."));

		mockMvc.perform(put("/point")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(requestDto)))
				.andExpect(status().isBadRequest());
	}
}
