package org.example.boxingarena.dto.match;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.boxingarena.domain.MatchEndType;
import org.example.boxingarena.domain.MatchResult;

@Getter
@Setter
@Builder
public class DetailMatchResultResponse {

    private Long matchResultId;
    private Long tournamentId;
    private Long matchId;
    private Long winnerId;
    private MatchEndType matchEndType;
    private String score;

    public static DetailMatchResultResponse from(MatchResult matchResult) {
        return DetailMatchResultResponse.builder()
                .matchResultId(matchResult.getId())
                .matchId(matchResult.getMatchId())
                .tournamentId(matchResult.getTournamentId())
                .winnerId(matchResult.getWinnerId())
                .matchEndType(matchResult.getMatchEndType())
                .score(matchResult.getScore())
                .build();
    }
}
