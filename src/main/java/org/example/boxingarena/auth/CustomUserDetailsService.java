package org.example.boxingarena.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.domain.Player;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.exception.ErrorCode;
import org.example.boxingarena.repository.PlayerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final PlayerRepository playerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Player player = playerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.PLAYER_NOT_FOUND));

        return new CustomUserDetails(player);
    }

}
