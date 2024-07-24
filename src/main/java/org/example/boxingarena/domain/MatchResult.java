package org.example.boxingarena.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MatchResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tournamentId;

    private Long matchId;

    private Long winnerId;

    @Enumerated(EnumType.STRING)
    private MatchEndType matchEndType;

    private String score; // json 형식으로 저장할 것임

    public MatchResult(Long tournamentId, Long matchId, Long winnerId, MatchEndType matchEndType, String score) {
        this.tournamentId = tournamentId;
        this.matchId = matchId;
        this.winnerId = winnerId;
        this.matchEndType = matchEndType;
        this.score = score;
    }
}
