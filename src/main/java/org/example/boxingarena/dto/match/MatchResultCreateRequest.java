package org.example.boxingarena.dto.match;

import lombok.Getter;
import lombok.Setter;
import org.example.boxingarena.domain.MatchEndType;

@Getter
@Setter
public class MatchResultCreateRequest {

    private Long tournamentId;
    private Long matchId;
    private Long winnerId;
    private MatchEndType matchEndType;
    private String score;

}
