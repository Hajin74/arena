package org.example.boxingarena.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.domain.Match;
import org.example.boxingarena.domain.MatchEndType;
import org.example.boxingarena.domain.MatchResult;
import org.example.boxingarena.domain.MatchStatus;
import org.example.boxingarena.repository.MatchRepository;
import org.example.boxingarena.repository.MatchResultRepository;
import org.example.boxingarena.repository.PlayerRepository;
import org.example.boxingarena.repository.TournamentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchResultService {

    private PlayerRepository playerRepository;
    private TournamentRepository tournamentRepository;
    private MatchRepository matchRepository;
    private MatchResultRepository matchResultRepository;

    @Transactional
    public void recordMatchScore(Long matchId, Long winnerId, MatchEndType matchEndType, String score) {
        Optional<Match> onGoingMatch = matchRepository.findById(matchId);
        if (onGoingMatch.isEmpty()) {
            // todo: 예외 처리
            log.info("Invalid match ID: " + matchId);
            return;
        }

        if (!onGoingMatch.get().getStatus().equals(MatchStatus.ONGOING)) {
            // todo: 예외 처리
            log.info("Invalid match status");
            return;
        }

        if (!playerRepository.existsById(winnerId)) {
            // todo : 예외처리
            log.info("Invalid winnerId ID: " + winnerId);
        }

        // 경기 기록 저장
        MatchResult matchResult = new MatchResult(matchId, winnerId, matchEndType, score);
        matchResultRepository.save(matchResult);

        // 경기 종료
        onGoingMatch.get().closeMatch();
    }

}
