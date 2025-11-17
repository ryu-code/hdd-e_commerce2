package kr.hhplus.be.server.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.hhplus.be.domain.point.entity.PointDto;
import kr.hhplus.be.domain.user.entity.UserDto;
import kr.hhplus.be.domain.user.entity.UserResponse;
import kr.hhplus.be.domain.user.facade.UserFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("UserController getUser API TDD Tests")
class UserControllerGetUserTests {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private UserFacade userFacade;

	@Test
	@DisplayName("사용자 조회 성공")
	void testGetUserSuccess() throws Exception {
		// Given
		Long userId = 1L;
		UserDto userDto = UserDto.builder()
				.userId(userId)
				.userName("김철수")
				.userEmail("kim@example.com")
				.createdAt(null)
				.build();

		PointDto pointDto = PointDto.builder()
				.pointId(1L)
				.userId(userId)
				.point(10000L)
				.build();

		UserResponse userResponse = UserResponse.builder()
				.user(userDto)
				.userPoint(pointDto)
				.build();

		when(userFacade.getUser(userId)).thenReturn(userResponse);

		// When & Then - MockMvc 사용
		mockMvc.perform(get("/user/{userId}", userId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.user.userId", equalTo(userId.intValue())))
				.andExpect(jsonPath("$.user.userName", equalTo("김철수")))
				.andExpect(jsonPath("$.userPoint.point", equalTo(10000)));
	}

	@Test
	@DisplayName("사용자 조회 성공 - 포인트가 0인 경우")
	void testGetUserSuccessWithZeroPoint() throws Exception {
		// Given
		Long userId = 2L;
		UserDto userDto = UserDto.builder()
				.userId(userId)
				.userName("이영희")
				.userEmail("lee@example.com")
				.createdAt(null)
				.build();

		PointDto pointDto = PointDto.builder()
				.userId(userId)
				.point(0L)
				.build();

		UserResponse userResponse = UserResponse.builder()
				.user(userDto)
				.userPoint(pointDto)
				.build();

		when(userFacade.getUser(userId)).thenReturn(userResponse);

		// When
		ResultActions result = mockMvc.perform(get("/user/{userId}", userId)
				.contentType(MediaType.APPLICATION_JSON));

		// Then
		result.andExpect(status().isOk())
				.andExpect(jsonPath("$.userPoint.point", equalTo(0)));
	}

	@Test
	@DisplayName("사용자 조회 실패 - 사용자가 존재하지 않음")
	void testGetUserNotFound() throws Exception {
		// Given
		Long userId = 999L;

		when(userFacade.getUser(userId))
				.thenThrow(new IllegalArgumentException("User not found"));

		// When & Then
		mockMvc.perform(get("/user/{userId}", userId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());

		verify(userFacade, times(1)).getUser(userId);
	}

	@Test
	@DisplayName("사용자 조회 실패 - 유효하지 않은 userId (음수)")
	void testGetUserInvalidNegativeId() throws Exception {
		// Given
		Long userId = -1L;

		when(userFacade.getUser(userId))
				.thenThrow(new IllegalArgumentException("Invalid userId"));

		// When & Then
		mockMvc.perform(get("/user/{userId}", userId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	@DisplayName("사용자 조회 실패 - userId가 0인 경우")
	void testGetUserZeroId() throws Exception {
		// Given
		Long userId = 0L;

		when(userFacade.getUser(userId))
				.thenThrow(new IllegalArgumentException("Invalid userId"));

		// When & Then
		mockMvc.perform(get("/user/{userId}", userId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	@DisplayName("사용자 조회 - 응답 구조 검증")
	void testGetUserResponseStructure() throws Exception {
		// Given
		Long userId = 1L;
		UserDto userDto = UserDto.builder()
				.userId(userId)
				.userName("최테스트")
				.userEmail("test@example.com")
				.build();

		PointDto pointDto = PointDto.builder()
				.userId(userId)
				.point(5000L)
				.build();

		UserResponse userResponse = UserResponse.builder()
				.user(userDto)
				.userPoint(pointDto)
				.build();

		when(userFacade.getUser(userId)).thenReturn(userResponse);

		// When
		ResultActions result = mockMvc.perform(get("/users/{userId}", userId)
				.contentType(MediaType.APPLICATION_JSON));

		// Then
		result.andExpect(status().isOk())
				.andExpect(jsonPath("$.user", notNullValue()))
				.andExpect(jsonPath("$.userPoint", notNullValue()))
				.andExpect(jsonPath("$.user.userId", notNullValue()))
				.andExpect(jsonPath("$.user.useruserName", notNullValue()))
				.andExpect(jsonPath("$.user.useruserEmail", notNullValue()))
				.andExpect(jsonPath("$.userPoint.userId", notNullValue()))
				.andExpect(jsonPath("$.userPoint.point", notNullValue()));
	}

	@Test
	@DisplayName("사용자 조회 - Content-Type 검증")
	void testGetUserContentType() throws Exception {
		// Given
		Long userId = 1L;
		UserDto userDto = UserDto.builder()
				.userId(userId)
				.userName("정성호")
				.userEmail("jung@example.com")
				.build();

		PointDto pointDto = PointDto.builder()
				.userId(userId)
				.point(15000L)
				.build();

		UserResponse userResponse = UserResponse.builder()
				.user(userDto)
				.userPoint(pointDto)
				.build();

		when(userFacade.getUser(userId)).thenReturn(userResponse);

		// When
		ResultActions result = mockMvc.perform(get("/users/{userId}", userId)
				.contentType(MediaType.APPLICATION_JSON));

		// Then
		result.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	@DisplayName("사용자 조회 - Facade 호출 검증")
	void testGetUserFacadeInvocation() throws Exception {
		// Given
		Long userId = 5L;
		UserDto userDto = UserDto.builder()
				.userId(userId)
				.userName("조테스터")
				.userEmail("jo@example.com")
				.build();

		PointDto pointDto = PointDto.builder()
				.userId(userId)
				.point(20000L)
				.build();

		UserResponse userResponse = UserResponse.builder()
				.user(userDto)
				.userPoint(pointDto)
				.build();

		when(userFacade.getUser(userId)).thenReturn(userResponse);

		// When
		mockMvc.perform(get("/users/{userId}", userId)
				.contentType(MediaType.APPLICATION_JSON));

		// Then - Facade의 getUser 메서드가 정확히 한 번 호출되었는지 검증
		verify(userFacade, times(1)).getUser(userId);
	}

	@Test
	@DisplayName("사용자 조회 - 매우 큰 userId 처리")
	void testGetUserLargeId() throws Exception {
		// Given
		Long userId = Long.MAX_VALUE - 1;
		UserDto userDto = UserDto.builder()
				.userId(userId)
				.userName("범준호")
				.userEmail("beom@example.com")
				.build();

		PointDto pointDto = PointDto.builder()
				.userId(userId)
				.point(999999999L)
				.build();

		UserResponse userResponse = UserResponse.builder()
				.user(userDto)
				.userPoint(pointDto)
				.build();

		when(userFacade.getUser(userId)).thenReturn(userResponse);

		// When
		ResultActions result = mockMvc.perform(get("/users/{userId}", userId)
				.contentType(MediaType.APPLICATION_JSON));

		// Then
		result.andExpect(status().isOk())
				.andExpect(jsonPath("$.user.userId", equalTo(userId.intValue())));
	}
}