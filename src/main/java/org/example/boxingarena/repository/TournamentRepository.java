package org.example.boxingarena.repository;

import org.example.boxingarena.domain.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    boolean existsById(Long id);
    Optional<Tournament> findById(Long id);
    List<Tournament> findAllByOrganizerId(Long organizerId);
}
