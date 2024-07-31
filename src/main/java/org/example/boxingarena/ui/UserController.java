package org.example.boxingarena.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.auth.CustomUserDetails;
import org.example.boxingarena.dto.user.PlayerResponse;
import org.example.boxingarena.dto.user.UserDetailResponse;
import org.example.boxingarena.dto.user.UserJoinRequest;
import org.example.boxingarena.dto.user.UserUpdateRequest;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public String join(@RequestBody UserJoinRequest request) {
        log.info("join - {}", request.toString());
        userService.join(request);

        return "join success!!";
    }

    @PatchMapping()
    public void update(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody UserUpdateRequest request) {
        log.info("update api - {}", request.toString());

        try {
            userService.update(customUserDetails, request);
        }  catch (CustomException exception) {
            log.info("update - exception : " + exception.getMessage());
        }
    }

    @GetMapping
    public UserDetailResponse getUser(@RequestHeader("access") String access) {
        log.info("getUser - api : " + access);

        try {
            return userService.getUser(access);
        } catch (CustomException exception) {
            log.info("getUser - exception : " + exception.getMessage());
            return null;
        }
    }

    @GetMapping("/player/all")
    public List<PlayerResponse> getAllPlayer() {
        log.info("getAllPlayer - api");

        try {
            return userService.getAllPlayer();
        } catch (CustomException exception) {
            log.info("getAllPlayer - exception : " + exception.getMessage());
            return null;
        }
    }

}
