package org.example.boxingarena.dto.application;

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
    private String playerName;
    private ApplicationStatus applicationStatus;

    public static DetailApplicationResponse from(Application application, String playerName) {
        return DetailApplicationResponse.builder()
                .applicationId(application.getId())
                .tournamentId(application.getTournamentId())
                .playerId(application.getPlayerId())
                .playerName(playerName)
                .applicationStatus(application.getStatus())
                .build();
    }

    public static DetailApplicationResponse from(Application application) {
        return DetailApplicationResponse.builder()
                .applicationId(application.getId())
                .tournamentId(application.getTournamentId())
                .playerId(application.getPlayerId())
                .applicationStatus(application.getStatus())
                .build();
    }

}
