package kr.hhplus.be.server.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hhplus.be.server.domain.point.entity.PointDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {

    @JsonProperty("user")
    private UserDto user;

    @JsonProperty("userPoint")
    private long userPoint;
}