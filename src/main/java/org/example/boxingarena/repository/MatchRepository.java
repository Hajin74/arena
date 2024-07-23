package org.example.boxingarena.repository;

import org.example.boxingarena.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {

}
