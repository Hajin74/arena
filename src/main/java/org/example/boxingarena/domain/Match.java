package org.example.boxingarena.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "boxing_match")
@Getter
@NoArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tournamentId;

    private short totalRoundsCount;

    private Long redCornerPlayerId;

    private Long blueCornerPlayerId;

    private String judgeName;

    @Enumerated(EnumType.STRING)
    private MatchStatus status;

    public Match(Long tournamentId, short totalRoundsCount, Long redCornerPlayerId,
                 Long blueCornerPlayerId, String judgeName) {
        this.tournamentId = tournamentId;
        this.totalRoundsCount = totalRoundsCount;
        this.redCornerPlayerId = redCornerPlayerId;
        this.blueCornerPlayerId = blueCornerPlayerId;
        this.judgeName = judgeName;
        this.status = MatchStatus.SCHEDULED;
    }

}
