package org.example.boxingarena.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.domain.Application;
import org.example.boxingarena.domain.ApplicationStatus;
import org.example.boxingarena.domain.Match;
import org.example.boxingarena.domain.MatchStatus;
import org.example.boxingarena.dto.MatchCreateRequest;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.exception.ErrorCode;
import org.example.boxingarena.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchService {

    private final UserRepository userRepository;
    private final TournamentRepository tournamentRepository;
    private final MatchRepository matchRepository;

    @Transactional
    public void registerMatch(Long organizerId, MatchCreateRequest request) {
        log.info("registerMatch - service");

        if (!userRepository.existsById(organizerId)) {
            throw new CustomException(ErrorCode.ORGANIZER_NOT_FOUND);
        }

        if (!tournamentRepository.existsById(request.getTournamentId())) {
            throw new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND);
        }

        if (!userRepository.existsById(request.getRedCornerPlayerId())) {
            throw new CustomException(ErrorCode.PLAYER_NOT_FOUND);
        }

        if (!userRepository.existsById(request.getBlueCornerPlayerId())) {
            throw new CustomException(ErrorCode.PLAYER_NOT_FOUND);
        }

        Match scheduledMatch = new Match(request.getTournamentId(), request.getRedCornerPlayerId(),
                request.getBlueCornerPlayerId(), request.getJudgeName());
        matchRepository.save(scheduledMatch);
    }

    public List<Match> getMatchesByTournamentAndPlayer(Long tournamentId, Long playerId) {
        if (!tournamentRepository.existsById(tournamentId)) {
            throw new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND);
        }

        if (!userRepository.existsById(playerId)) {
            throw new CustomException(ErrorCode.PLAYER_NOT_FOUND);
        }

        return matchRepository.findAllByTournamentIdAndRedCornerPlayerIdOrBlueCornerPlayerId(tournamentId, playerId, playerId);
    }

}
