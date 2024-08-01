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

    private Long redCornerApplicationId;

    private Long blueCornerApplicationId;

    private Long redCornerPlayerId;
    private Long blueCornerPlayerId;

    private String groupId;

    @Enumerated(EnumType.STRING)
    private MatchType type;

    @Builder
    public Match(Long tournamentId, Long redCornerApplicationId, Long blueCornerApplicationId, String groupId, MatchType type) {
        this.tournamentId = tournamentId;
        this.redCornerApplicationId = redCornerApplicationId;
        this.blueCornerApplicationId = blueCornerApplicationId;
        this.groupId = groupId;
        this.type = type;
    }

}
