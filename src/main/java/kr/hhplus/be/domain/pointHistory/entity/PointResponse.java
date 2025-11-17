package kr.hhplus.be.domain.pointHistory.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hhplus.be.domain.point.entity.PointDto;
import kr.hhplus.be.domain.user.entity.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PointResponse {

    @JsonProperty("userPoint")
    private PointDto userPoint;
}