package org.example.boxingarena.ui;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.dto.ApplicationFormRequest;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.service.ApplicationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public void applyForTournament(Long playerId, @RequestBody @Valid ApplicationFormRequest request) {
        log.info("applyForTournament api");

        try {
            applicationService.applyForTournament(playerId, request);
        } catch (CustomException exception) {
            return;
        }
    }

}
