package org.example.boxingarena.repository;

import org.example.boxingarena.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    boolean existsById(Long id);

    boolean existsByEmail(String email);
    Optional<Player> findById(Long id);
}
