package org.example.boxingarena.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.boxingarena.domain.Application;
import org.example.boxingarena.domain.ApplicationStatus;

@Getter
@Setter
@Builder
public class DetailApplicationResponse {

    private Long applicationId;
    private Long tournamentId;
    private Long playerId;
    private ApplicationStatus applicationStatus;

    public static DetailApplicationResponse from(Application application) {
        return DetailApplicationResponse.builder()
                .applicationId(application.getId())
                .tournamentId(application.getTournamentId())
                .playerId(application.getPlayerId())
                .applicationStatus(application.getStatus())
                .build();
    }

}
