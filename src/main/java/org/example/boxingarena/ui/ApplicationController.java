package org.example.boxingarena.ui;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.auth.CustomUserDetails;
import org.example.boxingarena.dto.application.ApplicationFormRequest;
import org.example.boxingarena.dto.application.DetailApplicationResponse;
import org.example.boxingarena.dto.application.MyApplicationListResponse;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.service.ApplicationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public void applyForTournament(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody @Valid ApplicationFormRequest request) {
        log.info("applyForTournament - api");

        try {
            applicationService.applyForTournament(customUserDetails, request);
        } catch (CustomException exception) {
            log.info("applyForTournament - exception : " + exception.getMessage());
        }
    }

    @PatchMapping("/{applicationId}/approve")
    public void approveApplication(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long applicationId) {
        log.info("approveApplication - api");

        try {
            applicationService.approveApplication(customUserDetails, applicationId);
        } catch (CustomException exception) {
            log.info("approveApplication - exception : " + exception.getMessage());
        }
    }

    @PatchMapping("/{applicationId}/reject")
    public void rejectApplication(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long applicationId) {
        log.info("rejectApplication - api");

        try {
            applicationService.rejectApplication(customUserDetails, applicationId);
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
            log.info("getApplication - exception : " + exception.getMessage());
            return null;
        }
    }

    @GetMapping("/player")
    public List<MyApplicationListResponse> getApplicationsByPlayer(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info("getApplicationsByPlayer - api");

        try {
            return applicationService.getApplicationsByPlayer(customUserDetails);
        } catch (CustomException exception) {
            return null;
        }
    }

    @GetMapping("/tournament/{tournamentId}")
    public List<DetailApplicationResponse> getApplicationsForTournament(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long tournamentId) {
        log.info("getApplicationsForTournament - api");

        try {
            return applicationService.getApplicationsForTournament(customUserDetails, tournamentId);
        } catch (CustomException exception) {
            return null;
        }
    }

}
