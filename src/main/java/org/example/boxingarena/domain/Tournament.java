package org.example.boxingarena.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.boxingarena.dto.tournament.TournamentUpdateRequest;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private LocalDate startDate;

    private LocalDate endDate;

    private String posterImgUrl;

    private int totalRoundsCount;

    private String intro;

    @Enumerated(EnumType.STRING)
    private TournamentStatus status;

    private Long organizerId;

    @Builder
    public Tournament(String name, String location, LocalDate startDate, LocalDate endDate, String posterImgUrl, int totalRoundsCount, String intro, Long organizerId) {
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.posterImgUrl = posterImgUrl;
        this.totalRoundsCount = totalRoundsCount;
        this.organizerId = organizerId;
        this.intro = intro;
        this.status = TournamentStatus.SCHEDULED;
    }

    public void beginApplicationPeriod() {
        this.status = TournamentStatus.APPLICATION_OPENING;
    }

    public void endApplicationPeriod() {
        this.status = TournamentStatus.APPLICATION_CLOSED;
    }

    public void beginTournament() {
        this.status = TournamentStatus.ONGOING;
    }

    public void endTournament() {
        this.status = TournamentStatus.COMPLETED;
    }

    public void updateTournament(TournamentUpdateRequest request) {
        this.name = request.getName();
        this.location = request.getLocation();
        this.startDate = request.getStartDate();
        this.endDate = request.getEndDate();
        this.posterImgUrl = request.getPosterImgUrl();
        this.intro = request.getIntro();
    }

}
