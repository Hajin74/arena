package org.example.boxingarena.repository;

import org.example.boxingarena.domain.Application;
import org.example.boxingarena.domain.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findAllByPlayerId(Long playerId);

    List<Application> findAllByTournamentId(Long tournamentId);
    List<Application> findAllByTournamentIdAndStatus(Long tournamentId, ApplicationStatus status);

}
