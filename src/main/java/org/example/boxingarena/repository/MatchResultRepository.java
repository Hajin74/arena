package org.example.boxingarena.repository;

import org.example.boxingarena.domain.MatchResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchResultRepository extends JpaRepository<MatchResult, Long> {

    List<MatchResult> findAllByTournamentId(Long tournamentId);

}
