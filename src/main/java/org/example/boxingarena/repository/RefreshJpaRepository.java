package org.example.boxingarena.repository;

import org.example.boxingarena.domain.Refresh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshJpaRepository extends JpaRepository<Refresh, Long> {

    Boolean existsByToken(String token);

    @Transactional
    void deleteByToken(String token);

}
