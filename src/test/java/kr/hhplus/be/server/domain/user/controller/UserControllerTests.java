//package kr.hhplus.be.server.domain.user.controller;
//
//import kr.hhplus.be.domain.user.facade.UserFacade;
//import kr.hhplus.be.domain.user.entity.UserDto;
//import kr.hhplus.be.domain.point.entity.PointDto;
//import kr.hhplus.be.domain.user.entity.UserResponse;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.testcontainers.shaded.com.google.common.base.Predicates.equalTo;
//
//@AutoConfigureMockMvc(addFilters = false)
//@WebMvcTest(kr.hhplus.be.domain.user.controller.UserController.class)
//@DisplayName("UserController getUser API Tests")
//class UserControllerGetUserTests {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private UserFacade userFacade;
//
//
//	@Test
//	@DisplayName("사용자 조회 성공")
//	void testGetUserSuccess() throws Exception {
//		Long userId = 1L;
//
//		UserDto userDto = UserDto.builder()
//				.userId(userId)
//				.userName("김철수")
//				.userEmail("kim@example.com")
//				.build();
//
//		PointDto pointDto = PointDto.builder()
//				.point(10000L)
//				.userId(userId)
//				.build();
//
//		UserResponse response = UserResponse.builder()
//				.user(userDto)
//				.userPoint(pointDto)
//				.build();
//
//		when(userFacade.getUser(userId)).thenReturn(response);
//
//		mockMvc.perform(get("/user/{userId}", userId)
//						.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect((ResultMatcher) jsonPath("$.user.userId", equalTo(userId.longValue())))
//				.andExpect((ResultMatcher) jsonPath("$.user.userName", equalTo("김철수")))
//				.andExpect((ResultMatcher) jsonPath("$.userPoint.point", equalTo(10000)));
//	}
//
//	@Test
//	@DisplayName("포인트 0일 때 정상 반환")
//	void testGetUserZeroPoint() throws Exception {
//		Long userId = 2L;
//
//		UserDto userDto = UserDto.builder()
//				.userId(userId)
//				.userName("이영희")
//				.userEmail("lee@example.com")
//				.build();
//
//		PointDto pointDto = PointDto.builder()
//				.userId(userId)
//				.point(0L)
//				.build();
//
//		UserResponse response = UserResponse.builder()
//				.user(userDto)
//				.userPoint(pointDto)
//				.build();
//
//		when(userFacade.getUser(userId)).thenReturn(response);
//
//		mockMvc.perform(get("/user/{userId}", userId))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.userPoint.point", equalTo(0)));
//	}
//
//	@Test
//	@DisplayName("존재하지 않는 사용자 → 4xx")
//	void testGetUserNotFound() throws Exception {
//		Long userId = 999L;
//
//		when(userFacade.getUser(userId))
//				.thenThrow(new IllegalArgumentException("User not found"));
//
//		mockMvc.perform(get("/user/{userId}", userId))
//				.andExpect(status().is4xxClientError());
//
//		verify(userFacade, times(1)).getUser(userId);
//	}
//
//	@Test
//	@DisplayName("음수 userId → 4xx")
//	void testInvalidNegativeUserId() throws Exception {
//		Long userId = -1L;
//
//		when(userFacade.getUser(userId))
//				.thenThrow(new IllegalArgumentException("Invalid userId"));
//
//		mockMvc.perform(get("/user/{userId}", userId))
//				.andExpect(status().is4xxClientError());
//	}
//
//	@Test
//	@DisplayName("userId = 0 → 4xx")
//	void testZeroUserId() throws Exception {
//		Long userId = 0L;
//
//		when(userFacade.getUser(userId))
//				.thenThrow(new IllegalArgumentException("Invalid userId"));
//
//		mockMvc.perform(get("/user/{userId}", userId))
//				.andExpect(status().is4xxClientError());
//	}
//
//	@Test
//	@DisplayName("응답 구조 검증")
//	void testResponseStructure() throws Exception {
//		Long userId = 1L;
//
//		UserDto userDto = UserDto.builder()
//				.userId(userId)
//				.userName("최테스트")
//				.userEmail("test@example.com")
//				.build();
//
//		PointDto pointDto = PointDto.builder()
//				.userId(userId)
//				.point(5000L)
//				.build();
//
//		UserResponse response = UserResponse.builder()
//				.user(userDto)
//				.userPoint(pointDto)
//				.build();
//
//		when(userFacade.getUser(userId)).thenReturn(response);
//
//		mockMvc.perform(get("/user/{userId}", userId))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.user").exists())
//				.andExpect(jsonPath("$.userPoint").exists())
//				.andExpect(jsonPath("$.user.userId").value(userId.intValue()))
//				.andExpect(jsonPath("$.user.userName").value("최테스트"))
//				.andExpect(jsonPath("$.user.userEmail").value("test@example.com"))
//				.andExpect(jsonPath("$.userPoint.point").value(5000));
//	}
//
//	@Test
//	@DisplayName("Content-Type JSON 정상 반환")
//	void testContentType() throws Exception {
//		Long userId = 1L;
//
//		UserResponse response = UserResponse.builder()
//				.user(UserDto.builder()
//						.userId(userId)
//						.userName("정성호")
//						.userEmail("jung@example.com")
//						.build())
//				.userPoint(PointDto.builder()
//						.userId(userId)
//						.point(15000L)
//						.build())
//				.build();
//
//		when(userFacade.getUser(userId)).thenReturn(response);
//
//		mockMvc.perform(get("/user/{userId}", userId))
//				.andExpect(status().isOk())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
//	}
//
//	@Test
//	@DisplayName("Facade 호출 검증: 1회 호출")
//	void testFacadeCall() throws Exception {
//		Long userId = 5L;
//
//		UserResponse response = UserResponse.builder()
//				.user(UserDto.builder()
//						.userId(userId)
//						.userName("조테스터")
//						.userEmail("jo@example.com")
//						.build())
//				.userPoint(PointDto.builder()
//						.userId(userId)
//						.point(20000L)
//						.build())
//				.build();
//
//		when(userFacade.getUser(userId)).thenReturn(response);
//
//		mockMvc.perform(get("/user/{userId}", userId))
//				.andExpect(status().isOk());
//
//		verify(userFacade, times(1)).getUser(userId);
//	}
//
//	@Test
//	@DisplayName("매우 큰 userId 테스트")
//	void testLargeUserId() throws Exception {
//		Long userId = Long.MAX_VALUE - 1;
//
//		UserResponse response = UserResponse.builder()
//				.user(UserDto.builder()
//						.userId(userId)
//						.userName("범준호")
//						.userEmail("beom@example.com")
//						.build())
//				.userPoint(PointDto.builder()
//						.userId(userId)
//						.point(999999999L)
//						.build())
//				.build();
//
//		when(userFacade.getUser(userId)).thenReturn(response);
//
//		mockMvc.perform(get("/user/{userId}", userId))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.user.userId").value(userId.intValue()));
//	}
//}
