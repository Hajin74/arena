package org.example.boxingarena.dto.match;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.boxingarena.domain.Match;
import org.example.boxingarena.domain.MatchType;

@Getter
@Setter
@Builder
public class MatchCreateRequest {

    private Long tournamentId;
    private Long redCornerPlayerId;
    private Long blueCornerPlayerId;
    private String groupId;
    private MatchType type;

}
