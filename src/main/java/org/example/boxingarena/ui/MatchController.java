package org.example.boxingarena.ui;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.dto.MatchCreateRequest;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.service.MatchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/match")
public class MatchController {

    private final MatchService matchService;

    @PostMapping
    public void registerMatch(Long organizerId, @RequestBody @Valid MatchCreateRequest request) {
        log.info("registerMatch - api");

        try {
            matchService.registerMatch(organizerId, request);
        } catch (CustomException exception) {
            return;
        }
    }
}
