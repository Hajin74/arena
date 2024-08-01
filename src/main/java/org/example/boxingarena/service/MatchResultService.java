package org.example.boxingarena.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.domain.Match;
import org.example.boxingarena.domain.MatchResult;
import org.example.boxingarena.dto.match.MatchResultCreateRequest;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.exception.ErrorCode;
import org.example.boxingarena.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchResultService {

    private final UserRepository userRepository;
    private final TournamentRepository tournamentRepository;
    private final MatchRepository matchRepository;
    private final MatchResultRepository matchResultRepository;

    @Transactional
    public void recordMatchScore(Long organizerId, MatchResultCreateRequest request) {
        log.info("recordMatchScore - service");

        if (!userRepository.existsById(organizerId)) {
            throw new CustomException(ErrorCode.ORGANIZER_NOT_FOUND);
        }

        if (!tournamentRepository.existsById(request.getTournamentId())) {
            throw new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND);
        }

        Optional<Match> scheduledMatch = matchRepository.findById(request.getMatchId());
        if (scheduledMatch.isEmpty()) {
            throw new CustomException(ErrorCode.MATCH_NOT_FOUND);
        }

        if (!userRepository.existsById(request.getWinnerId())) {
            throw new CustomException(ErrorCode.PLAYER_NOT_FOUND);
        }

        // 경기 결과 저장
        MatchResult matchResult = new MatchResult(request.getTournamentId(), request.getMatchId(),
                request.getWinnerId(), request.getMatchEndType(), request.getScore());
        matchResultRepository.save(matchResult);

        // 경기 종료
    }

    // 대회 경기결과 모두보기
    @Transactional(readOnly = true)
    public List<MatchResult> getMatchesResultsByTournament(Long tournamentId) {
        return matchResultRepository.findAllByTournamentId(tournamentId);
    }

}
