package org.example.boxingarena.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.domain.Application;
import org.example.boxingarena.domain.Tournament;
import org.example.boxingarena.domain.TournamentStatus;
import org.example.boxingarena.dto.ApplicationFormRequest;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.exception.ErrorCode;
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

    private final PlayerRepository playerRepository;
    private final TournamentRepository tournamentRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public void applyForTournament(Long playerId, ApplicationFormRequest request) {
        Optional<Tournament> tournament = tournamentRepository.findById(request.getTournamentId());
        if (tournament.isEmpty()) {
            throw new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND);
        }

        if (!playerRepository.existsById(playerId)) {
            throw new CustomException(ErrorCode.PLAYER_NOT_FOUND);
        }

        if (!tournament.get().getStatus().equals(TournamentStatus.APPLICATION_OPENING)) {
            throw new CustomException(ErrorCode.APPLICATION_FOR_TOURNAMENT_PERIOD_CLOSED);
        }

        Application newApplication = new Application(request.getTournamentId(), playerId);
        applicationRepository.save(newApplication);
    }
}

