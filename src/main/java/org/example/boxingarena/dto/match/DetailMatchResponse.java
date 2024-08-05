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
    private String redCornerPlayerName;
    private Long blueCornerPlayerId;
    private String blueCornerPlayerName;
    private MatchType type;

    public static DetailMatchResponse from(Match match, String redCornerPlayerName, String blueCornerPlayerName) {
        return DetailMatchResponse.builder()
                .matchId(match.getId())
                .tournamentId(match.getTournamentId())
                .groupId(match.getGroupId())
                .redCornerPlayerId(match.getRedCornerPlayerId())
                .redCornerPlayerName(redCornerPlayerName)
                .blueCornerPlayerName(blueCornerPlayerName)
                .blueCornerPlayerId(match.getBlueCornerPlayerId())
                .type(match.getType())
                .build();
    }

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
