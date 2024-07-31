package org.example.boxingarena.dto.tournament;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.boxingarena.domain.Tournament;
import org.example.boxingarena.domain.TournamentStatus;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TournamentSummaryResponse {

    private Long tournamentId;
    private String name;
    private String posterImgUrl;
    private LocalDate startDate;
    private TournamentStatus tournamentStatus;

    public static TournamentSummaryResponse from(Tournament tournament) {
        return TournamentSummaryResponse.builder()
                .tournamentId(tournament.getId())
                .name(tournament.getName())
                .posterImgUrl(tournament.getPosterImgUrl())
                .startDate(tournament.getStartDate())
                .tournamentStatus(tournament.getStatus())
                .build();
    }

}
