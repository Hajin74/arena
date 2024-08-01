package org.example.boxingarena.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.auth.CustomUserDetails;
import org.example.boxingarena.domain.Match;
import org.example.boxingarena.domain.User;
import org.example.boxingarena.dto.match.MatchCreateRequest;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.exception.ErrorCode;
import org.example.boxingarena.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchService {

    private final UserRepository userRepository;
    private final TournamentRepository tournamentRepository;
    private final MatchRepository matchRepository;

    @Transactional
    public void registerMatch(CustomUserDetails customUserDetails, MatchCreateRequest request) {
        log.info("registerMatch - service");

        User organizer = userRepository.findByEmail(customUserDetails.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.ORGANIZER_NOT_FOUND));

        if (!tournamentRepository.existsById(request.getTournamentId())) {
            throw new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND);
        }

        if (!userRepository.existsById(request.getRedCornerPlayerId())) {
            throw new CustomException(ErrorCode.PLAYER_NOT_FOUND);
        }

        if (!userRepository.existsById(request.getBlueCornerPlayerId())) {
            throw new CustomException(ErrorCode.PLAYER_NOT_FOUND);
        }

        Match match = Match.builder()
                .tournamentId(request.getTournamentId())
                .redCornerPlayerId(request.getRedCornerPlayerId())
                .blueCornerPlayerId(request.getBlueCornerPlayerId())
                .groupId(request.getGroupId())
                .type(request.getType())
                .build();

        matchRepository.save(match);
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
