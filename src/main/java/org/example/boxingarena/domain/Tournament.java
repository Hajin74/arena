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

    @Enumerated(EnumType.STRING)
    private TournamentStatus status;

    private Long organizerId;

    public Tournament(String name, String location, LocalDate startDate, LocalDate endDate, Long organizerId) {
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.organizerId = organizerId;
        this.status = TournamentStatus.SCHEDULED;
    }


}
