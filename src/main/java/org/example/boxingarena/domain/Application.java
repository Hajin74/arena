package org.example.boxingarena.domain;

import jakarta.persistence.*;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tournamentId;

    private Long playerId;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

}
