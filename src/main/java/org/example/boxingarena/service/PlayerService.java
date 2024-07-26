package org.example.boxingarena.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.domain.Player;
import org.example.boxingarena.dto.PlayerJoinRequest;
import org.example.boxingarena.repository.PlayerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PlayerRepository playerRepository;

    @Transactional
    public void join(@RequestBody PlayerJoinRequest request) {
        boolean isExist = playerRepository.existsByEmail(request.getEmail());
        if (isExist) return;

        Player newPlayer = new Player(request.getPlayerName(), request.getEmail(),
                bCryptPasswordEncoder.encode(request.getPassword()), request.getGym());

        playerRepository.save(newPlayer);
    }
}
