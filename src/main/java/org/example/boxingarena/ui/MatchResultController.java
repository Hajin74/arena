package org.example.boxingarena.ui;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.dto.DetailMatchResultResponse;
import org.example.boxingarena.dto.MatchResultCreateRequest;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.service.MatchResultService;
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
    public void recordMatchScore(Long organizerId, @RequestBody @Valid MatchResultCreateRequest request) {
        log.info("recordMatchScore - api");

        try {
            matchResultService.recordMatchScore(organizerId, request);
        } catch (CustomException exception) {
            log.info("recordMatchScore - exception: " + exception.getMessage());
        }
    }

    @GetMapping
    public List<DetailMatchResultResponse> getMatchesResultsByTournament(Long tournamentId) {
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
