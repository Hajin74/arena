package org.example.boxingarena.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.boxingarena.domain.Tournament;
import org.example.boxingarena.domain.TournamentStatus;

@Getter
@Setter
@Builder
public class TournamentSummaryResponse {

    private Long tournamentId;
    private String name;
    private String posterImgUrl;
    private TournamentStatus tournamentStatus;

    public static TournamentSummaryResponse from(Tournament tournament) {
        return TournamentSummaryResponse.builder()
                .tournamentId(tournament.getId())
                .name(tournament.getName())
                .posterImgUrl(tournament.getPosterImgUrl())
                .tournamentStatus(tournament.getStatus())
                .build();
    }

}
