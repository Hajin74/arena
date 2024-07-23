package org.example.boxingarena.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MatchResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long matchId;

    private Long winnerId;

    private MatchEndType matchEndType;

    private String score; // json 형식으로 저장할 것임

    public MatchResult(Long matchId, Long winnerId, MatchEndType matchEndType, String score) {
        this.matchId = matchId;
        this.winnerId = winnerId;
        this.matchEndType = matchEndType;
        this.score = score;
    }
}