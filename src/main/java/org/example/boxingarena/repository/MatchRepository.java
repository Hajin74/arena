package org.example.boxingarena.repository;

import org.example.boxingarena.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findAllByTournamentIdAndRedCornerPlayerIdOrBlueCornerPlayerId(Long tournamentId, Long redCornerPlayerId, Long blueCornerPlayerId);

}
