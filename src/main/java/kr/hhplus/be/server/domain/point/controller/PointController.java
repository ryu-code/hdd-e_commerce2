package kr.hhplus.be.server.domain.point.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.hhplus.be.server.domain.point.entity.PointDto;
import kr.hhplus.be.server.domain.pointHistory.entity.PointResponse;
import kr.hhplus.be.server.domain.point.facade.PointFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointController {

    private final PointFacade pointFacade;

    @PutMapping("")
    public PointResponse addPoint(@RequestBody PointDto pointDto, HttpServletRequest httpServletRequest) {

        return pointFacade.addPoint(pointDto);
    }
}
