package org.example.boxingarena.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.auth.CustomUserDetails;
import org.example.boxingarena.auth.JwtUtil;
import org.example.boxingarena.domain.User;
import org.example.boxingarena.dto.user.PlayerResponse;
import org.example.boxingarena.dto.user.UserDetailResponse;
import org.example.boxingarena.dto.user.UserJoinRequest;
import org.example.boxingarena.dto.user.UserUpdateRequest;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.exception.ErrorCode;
import org.example.boxingarena.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public void join(@RequestBody UserJoinRequest request) {
        boolean isExist = userRepository.existsByEmail(request.getEmail());
        if (isExist) return;

        User newUser = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .role(request.getUserRole())
                .build();

        userRepository.save(newUser);
    }

    @Transactional
    public void update(CustomUserDetails customUserDetails, UserUpdateRequest request) {
        log.info("update - service");

        User user = userRepository.findByEmail(customUserDetails.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        user.updateUser(request);
    }

    @Transactional(readOnly = true)
    public UserDetailResponse getUser(String access) {
        log.info("getUser - service");

        if (access == null) {
            throw new CustomException(ErrorCode.ACCESS_NOT_FOUND);
        }

        String email = jwtUtil.getEmail(access);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return UserDetailResponse.from(user);
    }

    @Transactional(readOnly = true)
    public List<PlayerResponse> getAllPlayer() {
        log.info("getAllPlayer - service");

        return userRepository.findAllByRole("ROLE_PLAYER")
                .stream()
                .map(PlayerResponse::from)
                .collect(Collectors.toList());
    }

}
