package kr.hhplus.be.domain.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.hhplus.be.domain.user.entity.UserResponse;
import kr.hhplus.be.domain.user.facade.UserFacade;
import kr.hhplus.be.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserControlelr {

    private final UserFacade userFacade;

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable("userId") Long userId, HttpServletRequest httpServletRequest) {

        return userFacade.getUser(userId);
    }
}
