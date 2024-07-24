package org.example.boxingarena.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.boxingarena.domain.Match;

@Getter
@Setter
@Builder
public class MatchCreateRequest {

    private Long tournamentId;
    private Long redCornerPlayerId;
    private Long blueCornerPlayerId;
    private String judgeName;

}
