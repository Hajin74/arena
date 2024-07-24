package org.example.boxingarena.ui;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.dto.DetailMatchResponse;
import org.example.boxingarena.dto.MatchCreateRequest;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.service.MatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
            log.info("registerMatch - exception : " + exception.getMessage());
        }
    }

    @GetMapping
    public List<DetailMatchResponse> getMatchesByTournamentAndPlayer(Long tournamentId, Long playerId) {
        log.info("getMatchesByTournamentAndPlayer - api");

        try {
            return matchService.getMatchesByTournamentAndPlayer(tournamentId, playerId)
                    .stream()
                    .map(DetailMatchResponse::from)
                    .collect(Collectors.toList());
        } catch (CustomException exception) {
            return null;
        }
    }
}