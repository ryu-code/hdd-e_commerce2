package kr.hhplus.be.domain.point.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.hhplus.be.domain.point.entity.PointDto;
import kr.hhplus.be.domain.pointHistory.entity.PointResponse;
import kr.hhplus.be.domain.point.facade.PointFacade;
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
