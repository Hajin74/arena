package org.example.boxingarena.domain;

import jakarta.persistence.*;
import lombok.Builder;
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

    private Long redCornerPlayerId;

    private Long blueCornerPlayerId;

    private String groupId;

    @Enumerated(EnumType.STRING)
    private MatchType type;

    @Builder
    public Match(Long tournamentId, Long redCornerPlayerId, Long blueCornerPlayerId, String groupId, MatchType type) {
        this.tournamentId = tournamentId;
        this.redCornerPlayerId = redCornerPlayerId;
        this.blueCornerPlayerId = blueCornerPlayerId;
        this.groupId = groupId;
        this.type = type;
    }

}
