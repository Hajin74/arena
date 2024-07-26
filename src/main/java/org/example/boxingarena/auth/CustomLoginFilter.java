package org.example.boxingarena.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.domain.Refresh;
import org.example.boxingarena.dto.PlayerLoginRequest;
import org.example.boxingarena.repository.RefreshJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@Slf4j
public class CustomLoginFilter extends AbstractAuthenticationProcessingFilter {

    private JwtUtil jwtUtil;
    private RefreshJpaRepository refreshRepository;

    public CustomLoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, RefreshJpaRepository refreshRepository) {
        super(new AntPathRequestMatcher("/login", "POST"));
        setAuthenticationManager(authenticationManager);

        this.jwtUtil = jwtUtil;
        this.refreshRepository = refreshRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        log.info("attemptAuthentication - execute");

        ObjectMapper objectMapper = new ObjectMapper();
        String messageBody = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        log.info("messageBody : {}", messageBody);
        PlayerLoginRequest playerLoginRequest = objectMapper.readValue(messageBody, PlayerLoginRequest.class);

        String email = playerLoginRequest.getEmail();
        String password = playerLoginRequest.getPassword();

        log.info("attemptAuthentication - email: {}, password: {}", email, password);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);

        return getAuthenticationManager().authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        log.info("successfulAuthentication - execute!");

        String email = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();


        String access = jwtUtil.createJwt("access", email, role, 60000L);
        String refresh = jwtUtil.createJwt("refresh", email, role, 86400000L);

        addRefresh(email, refresh, 86400000L);

        response.addHeader("access", access);
        response.addCookie(createCookie("refresh", refresh));
        response.setStatus(HttpStatus.OK.value());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("unsuccessfulAuthentication - execute!");
        response.setStatus(401);
    }

    private void addRefresh(String email, String token, Long expiredMs) {
        Date date = new Date(System.currentTimeMillis() + expiredMs);
        Refresh refresh = Refresh.builder()
                .email(email)
                .token(token)
                .expiration(date.toString())
                .build();

        refreshRepository.save(refresh);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setHttpOnly(true);
        return cookie;
    }

}
