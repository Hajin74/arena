package org.example.boxingarena.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.boxingarena.domain.Tournament;
import org.example.boxingarena.domain.TournamentStatus;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TournamentDetailResponse {

    private Long tournamentId;
    private String name;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private String posterImgUrl;
    private String intro;
    private short totalRoundsCount;
    private TournamentStatus tournamentStatus;
    private Long organizerId;

    public static TournamentDetailResponse from(Tournament tournament) {
        return TournamentDetailResponse.builder()
                .tournamentId(tournament.getId())
                .name(tournament.getName())
                .location(tournament.getLocation())
                .startDate(tournament.getStartDate())
                .endDate(tournament.getEndDate())
                .posterImgUrl(tournament.getPosterImgUrl())
                .intro(tournament.getIntro())
                .totalRoundsCount(tournament.getTotalRoundsCount())
                .tournamentStatus(tournament.getStatus())
                .organizerId(tournament.getOrganizerId())
                .build();
    }

}
