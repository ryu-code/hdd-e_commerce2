package kr.hhplus.be.server.domain.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.hhplus.be.server.domain.user.entity.UserResponse;
import kr.hhplus.be.server.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable("userId") Long userId, HttpServletRequest httpServletRequest) {

        return userFacade.getUser(userId);
    }
}
