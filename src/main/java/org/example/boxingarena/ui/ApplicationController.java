package org.example.boxingarena.ui;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.dto.ApplicationFormRequest;
import org.example.boxingarena.dto.DetailApplicationResponse;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.service.ApplicationService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public void applyForTournament(Long playerId, @RequestBody @Valid ApplicationFormRequest request) {
        log.info("applyForTournament - api");

        try {
            applicationService.applyForTournament(playerId, request);
        } catch (CustomException exception) {
            log.info("applyForTournament - exception : " + exception.getMessage());
        }
    }

    @PatchMapping("/{applicationId}/approve")
    public void approveApplication(Long organizerId, @PathVariable Long applicationId) {
        log.info("approveApplication - api");

        try {
            applicationService.approveApplication(organizerId, applicationId);
        } catch (CustomException exception) {
            log.info("approveApplication - exception : " + exception.getMessage());
        }
    }

    @PatchMapping("/{applicationId}/reject")
    public void rejectApplication(Long playerId, @PathVariable Long applicationId) {
        log.info("rejectApplication - api");

        try {
            applicationService.rejectApplication(playerId, applicationId);
        } catch (CustomException exception) {
            return;
        }
    }

    @PatchMapping("/{applicationId}/cancel")
    public void cancelApplication(Long playerId, @PathVariable Long applicationId) {
        log.info("cancelApplication - api");

        try {
            applicationService.cancelApplication(playerId, applicationId);
        } catch (CustomException exception) {
            return;
        }
    }

    @GetMapping("/{applicationId}")
    public DetailApplicationResponse getApplication(@PathVariable Long applicationId) {
        log.info("getApplication - api");

        try {
            return applicationService.getApplication(applicationId);
        } catch (CustomException exception) {
            return null;
        }
    }

    @GetMapping
    public List<DetailApplicationResponse> getApplicationsByPlayer(Long playerId) {
        log.info("getApplicationsByPlayer - api");

        try {
            return applicationService.getApplicationsByPlayer(playerId);
        } catch (CustomException exception) {
            return null;
        }
    }

    @GetMapping("/tournament/{tournamentId}")
    public List<DetailApplicationResponse> getApplicationsForTournament(Long organizerId, @PathVariable Long tournamentId) {
        log.info("getApplicationsForTournament - api");

        try {
            return applicationService.getApplicationsForTournament(organizerId, tournamentId);
        } catch (CustomException exception) {
            return null;
        }
    }

}
