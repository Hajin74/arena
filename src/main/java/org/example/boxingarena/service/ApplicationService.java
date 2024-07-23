package org.example.boxingarena.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.domain.Application;
import org.example.boxingarena.domain.Tournament;
import org.example.boxingarena.domain.TournamentStatus;
import org.example.boxingarena.repository.ApplicationRepository;
import org.example.boxingarena.repository.OrganizerRepository;
import org.example.boxingarena.repository.PlayerRepository;
import org.example.boxingarena.repository.TournamentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationService {

    private OrganizerRepository organizerRepository;
    private PlayerRepository playerRepository;
    private TournamentRepository tournamentRepository;
    private ApplicationRepository applicationRepository;

    @Transactional
    public void applyForTournament(Long tournamentId, Long playerId) {
        Optional<Tournament> tournament = tournamentRepository.findById(tournamentId);
        if (tournament.isEmpty()) {
            // todo : 예외처리
            log.info("Invalid tournament ID: " + tournamentId);
            return;
        }

        if (!playerRepository.existsById(playerId)) {
            // todo : 예외처리
            log.info("Invalid player ID: " + playerId);
        }

        if (tournament.get().getStatus().equals(TournamentStatus.APPLICATION_OPENING)) {
            Application newApplication = new Application(tournamentId, playerId);
            applicationRepository.save(newApplication);
        }
    }
}

