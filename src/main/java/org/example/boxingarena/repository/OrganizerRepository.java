package org.example.boxingarena.repository;

import org.example.boxingarena.domain.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

    boolean existsById(Long id);
    Optional<Organizer> findById(Long id);

}
