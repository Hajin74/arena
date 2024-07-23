package org.example.boxingarena.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.domain.Match;
import org.example.boxingarena.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchService {

    private PlayerRepository playerRepository;
    private TournamentRepository tournamentRepository;
    private MatchRepository matchRepository;

    public void createMatch(Long tournamentId, short totalRoundsCount, Long redCornerPlayerId,
                            Long blueCornerPlayerId, String judgeName) {
        if (!tournamentRepository.existsById(tournamentId)) {
            // todo : 예외처리
            log.info("Invalid tournament ID: " + tournamentId);
        }

        if (!playerRepository.existsById(redCornerPlayerId)) {
            // todo : 예외처리
            log.info("Invalid redCornerPlayer ID: " + redCornerPlayerId);
        }

        if (!playerRepository.existsById(blueCornerPlayerId)) {
            // todo : 예외처리
            log.info("Invalid blueCornerPlayer ID: " + blueCornerPlayerId);
        }

        Match scheduledMath = new Match(tournamentId, totalRoundsCount, redCornerPlayerId, blueCornerPlayerId, judgeName);
        matchRepository.save(scheduledMath);
    }

    public List<Match> getMatch(Long tournamentId, Long playerId) {
        if (!tournamentRepository.existsById(tournamentId)) {
            // todo : 예외처리
            log.info("Invalid tournament ID: " + tournamentId);
        }

        if (!playerRepository.existsById(playerId)) {
            // todo : 예외처리
            log.info("Invalid player ID: " + playerId);
        }

        return matchRepository.findAllByTournamentIdAndRedCornerPlayerIdOrBlueCornerPlayerId(tournamentId, playerId, playerId);
    }

}
