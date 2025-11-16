package kr.hhplus.be.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    Long userId;

    String userName;

    String userEmail;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}
