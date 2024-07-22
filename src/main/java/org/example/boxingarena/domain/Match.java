package org.example.boxingarena.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "boxing_match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tournamentId;

    private short totalRoundsCount;

    private Long redCornerPlayerId;

    private Long blueCornerPlayerId;

    private String judgeName;

}
