package org.example.boxingarena.dto.match;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.boxingarena.domain.Match;
import org.example.boxingarena.domain.MatchStatus;

@Getter
@Setter
@Builder
public class DetailMatchResponse {

    private Long matchId;
    private Long tournamentId;
    private Long redCornerPlayerId;
    private Long blueCornerPlayerId;
    private String judgeName;
    private MatchStatus status;

    public static DetailMatchResponse from(Match match) {
        return DetailMatchResponse.builder()
                .matchId(match.getId())
                .tournamentId(match.getTournamentId())
                .redCornerPlayerId(match.getRedCornerPlayerId())
                .blueCornerPlayerId(match.getBlueCornerPlayerId())
                .judgeName(match.getJudgeName())
                .status(MatchStatus.SCHEDULED)
                .build();
    }

}
