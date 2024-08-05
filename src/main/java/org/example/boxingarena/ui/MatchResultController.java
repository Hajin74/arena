package org.example.boxingarena.ui;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.auth.CustomUserDetails;
import org.example.boxingarena.dto.match.DetailMatchResultResponse;
import org.example.boxingarena.dto.match.MatchResultCreateRequest;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.service.MatchResultService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/matchResult")
public class MatchResultController {

    private final MatchResultService matchResultService;

    @PostMapping
    public void recordMatchScore(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody @Valid MatchResultCreateRequest request) {
        log.info("recordMatchScore - api");

        try {
            matchResultService.recordMatchScore(customUserDetails, request);
        } catch (CustomException exception) {
            log.info("recordMatchScore - exception: " + exception.getMessage());
        }
    }

    @GetMapping("/tournament/{tournamentId}")
    public List<DetailMatchResultResponse> getMatchesResultsByTournament(@PathVariable Long tournamentId) {
        log.info("getMatchesResultsByTournament - api");

        try {
            return matchResultService.getMatchesResultsByTournament(tournamentId)
                    .stream()
                    .map(DetailMatchResultResponse::from)
                    .collect(Collectors.toList());
        } catch (CustomException exception) {
            log.info("getMatchesResultsByTournament - exception : " + exception.getMessage());
            return null;
        }
    }


}
