package org.example.boxingarena.dto.match;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.boxingarena.domain.Match;
import org.example.boxingarena.domain.MatchType;

@Getter
@Setter
@Builder
public class DetailMatchResponse {

    private Long matchId;
    private Long tournamentId;
    private String groupId;
    private Long redCornerPlayerId;
    private Long blueCornerPlayerId;
    private MatchType type;

    public static DetailMatchResponse from(Match match) {
        return DetailMatchResponse.builder()
                .matchId(match.getId())
                .tournamentId(match.getTournamentId())
                .groupId(match.getGroupId())
                .redCornerPlayerId(match.getRedCornerPlayerId())
                .blueCornerPlayerId(match.getBlueCornerPlayerId())
                .type(match.getType())
                .build();
    }

}
