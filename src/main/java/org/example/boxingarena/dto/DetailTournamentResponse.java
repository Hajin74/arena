package org.example.boxingarena.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.boxingarena.domain.Tournament;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class DetailTournamentResponse {

    private Long tournamentId;
    private String name;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long organizerId;

    public static DetailTournamentResponse from(Tournament tournament) {
        return DetailTournamentResponse.builder()
                .tournamentId(tournament.getId())
                .name(tournament.getName())
                .location(tournament.getLocation())
                .startDate(tournament.getStartDate())
                .endDate(tournament.getEndDate())
                .organizerId(tournament.getOrganizerId())
                .build();
    }

}
