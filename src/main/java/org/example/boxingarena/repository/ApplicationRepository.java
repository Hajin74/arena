package org.example.boxingarena.repository;

import org.example.boxingarena.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsById(Long id);
    Optional<Application> findById(Long id);
}
