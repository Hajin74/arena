package org.example.boxingarena.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.auth.CustomUserDetails;
import org.example.boxingarena.domain.Application;
import org.example.boxingarena.domain.Match;
import org.example.boxingarena.domain.User;
import org.example.boxingarena.dto.match.DetailMatchResponse;
import org.example.boxingarena.dto.match.MatchCreateRequest;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.exception.ErrorCode;
import org.example.boxingarena.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchService {

    private final UserRepository userRepository;
    private final TournamentRepository tournamentRepository;
    private final MatchRepository matchRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public void registerMatch(CustomUserDetails customUserDetails, MatchCreateRequest request) {
        log.info("registerMatch - service");

        User organizer = userRepository.findByEmail(customUserDetails.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.ORGANIZER_NOT_FOUND));

        if (!tournamentRepository.existsById(request.getTournamentId())) {
            throw new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND);
        }

        Application redCornerApplication = applicationRepository.findById(request.getRedCornerApplicationId())
                .orElseThrow(() -> new CustomException(ErrorCode.APPLICATION_NOT_FOUND));
        redCornerApplication.matchApplication();

        Application blueCornerApplication = applicationRepository.findById(request.getBlueCornerApplicationId())
                .orElseThrow(() -> new CustomException(ErrorCode.APPLICATION_NOT_FOUND));
        blueCornerApplication.matchApplication();

        Match match = Match.builder()
                .tournamentId(request.getTournamentId())
                .redCornerApplicationId(request.getRedCornerApplicationId())
                .blueCornerApplicationId(request.getBlueCornerApplicationId())
                .groupId(request.getGroupId())
                .type(request.getType())
                .build();

        matchRepository.save(match);
    }

    @Transactional
    public void deleteMatch(CustomUserDetails customUserDetails, Long matchId) {
        log.info("deleteMatch - service");

        User organizer = userRepository.findByEmail(customUserDetails.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.ORGANIZER_NOT_FOUND));

        Match match = matchRepository.findById(matchId)
                .orElseThrow(() ->  new CustomException(ErrorCode.MATCH_NOT_FOUND));

        Application redCornerApplication = applicationRepository.findById(match.getRedCornerApplicationId())
                .orElseThrow(() -> new CustomException(ErrorCode.APPLICATION_NOT_FOUND));
        redCornerApplication.approveApplication();

        Application blueCornerApplication = applicationRepository.findById(match.getBlueCornerApplicationId())
                .orElseThrow(() -> new CustomException(ErrorCode.APPLICATION_NOT_FOUND));
        blueCornerApplication.approveApplication();

        matchRepository.delete(match);
    }

    public List<DetailMatchResponse> getMatchesByTournament(Long tournamentId) {
        log.info("getMatchesByTournament - service");

        List<Match> matches = matchRepository.findAllByTournamentId(tournamentId);
        List<DetailMatchResponse> matchResponses = new ArrayList<>();
        for (Match match : matches) {

            Application redCornerApplication = applicationRepository.findById(match.getRedCornerApplicationId())
                    .orElseThrow(() -> new CustomException(ErrorCode.APPLICATION_NOT_FOUND));

            User redCornerPlayer = userRepository.findById(redCornerApplication.getPlayerId())
                    .orElseThrow(() -> new CustomException(ErrorCode.PLAYER_NOT_FOUND));

            Application blueCornerApplication = applicationRepository.findById(match.getBlueCornerApplicationId())
                    .orElseThrow(() -> new CustomException(ErrorCode.APPLICATION_NOT_FOUND));

            User blueCornerPlayer = userRepository.findById(blueCornerApplication.getPlayerId())
                    .orElseThrow(() -> new CustomException(ErrorCode.PLAYER_NOT_FOUND));

            matchResponses.add(DetailMatchResponse.from(match, redCornerPlayer.getName(), blueCornerPlayer.getName()));
        }

       return matchResponses;
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
