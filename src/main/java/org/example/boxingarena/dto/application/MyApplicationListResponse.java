package org.example.boxingarena.dto.application;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.boxingarena.domain.Application;
import org.example.boxingarena.domain.ApplicationStatus;

@Getter
@Setter
@Builder
public class MyApplicationListResponse {

    private Long applicationId;
    private Long tournamentId;
    private String tournamentName;
    private Long playerId;
    private String playerName;
    private ApplicationStatus applicationStatus;

    public static MyApplicationListResponse from(Application application, String tournamentName, String playerName) {
        return MyApplicationListResponse.builder()
                .applicationId(application.getId())
                .tournamentId(application.getTournamentId())
                .tournamentName(tournamentName)
                .playerId(application.getPlayerId())
                .playerName(playerName)
                .applicationStatus(application.getStatus())
                .build();
    }

}
