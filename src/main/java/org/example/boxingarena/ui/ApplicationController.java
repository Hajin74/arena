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
            return;
        }
    }

    @PatchMapping("/{applicationId}")
    public void approveApplication(Long playerId, @PathVariable Long applicationId) {
        log.info("approveApplication - api");

        try {
            applicationService.approveApplication(playerId, applicationId);
        } catch (CustomException exception) {
            return;
        }
    }

    @PatchMapping("/{applicationId}")
    public void rejectApplication(Long playerId, @PathVariable Long applicationId) {
        log.info("rejectApplication - api");

        try {
            applicationService.rejectApplication(playerId, applicationId);
        } catch (CustomException exception) {
            return;
        }
    }

    @PatchMapping("/{applicationId}")
    public void cancelApplication(Long playerId, @PathVariable Long applicationId) {
        log.info("cancelApplication - api");

        try {
            applicationService.cancelApplication(playerId, applicationId);
        } catch (CustomException exception) {
            return;
        }
    }

    @GetMapping
    public List<DetailApplicationResponse> getMyApplication(Long playerId) {
        log.info("getMyApplication - api");

        try {
            return applicationService.getMyApplication(playerId);
        } catch (CustomException exception) {
            return null;
        }
    }

}
