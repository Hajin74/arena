package org.example.boxingarena.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tournamentId;

    private Long playerId;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @Builder
    public Application(Long tournamentId, Long playerId) {
        this.tournamentId = tournamentId;
        this.playerId = playerId;
        this.status = ApplicationStatus.PENDING;
    }

    public void approveApplication() {
        this.status = ApplicationStatus.APPROVED;
    }

    public void rejectApplication() {
        this.status = ApplicationStatus.REJECTED;
    }

    public void cancelApplication() {
        this.status = ApplicationStatus.CANCEL;
    }

    public void matchApplication() {
        this.status = ApplicationStatus.MATCHED;
    }

}
