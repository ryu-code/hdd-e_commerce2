package kr.hhplus.be.server.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    Long userId;

    String userName;

    String userEmail;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}
