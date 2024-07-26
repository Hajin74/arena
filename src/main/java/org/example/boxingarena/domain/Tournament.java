package org.example.boxingarena.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private LocalDate startDate;

    private LocalDate endDate;

    private String posterImgUrl;

    private short totalRoundsCount;

    private String intro;

    @Enumerated(EnumType.STRING)
    private TournamentStatus status;

    private Long organizerId;

    public Tournament(String name, String location, LocalDate startDate, LocalDate endDate, String posterImgUrl, short totalRoundsCount, Long organizerId) {
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.posterImgUrl = posterImgUrl;
        this.totalRoundsCount = totalRoundsCount;
        this.organizerId = organizerId;
        this.status = TournamentStatus.SCHEDULED;
    }


}
