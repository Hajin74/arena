package org.example.boxingarena.ui;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.dto.DetailTournamentResponse;
import org.example.boxingarena.dto.TournamentCreateRequest;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.service.TournamentService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/tournament")
@RequiredArgsConstructor
public class TournamentController {

    private final TournamentService tournamentService;

    @PostMapping
    public void createTournament(Long organizerId, @RequestBody @Valid TournamentCreateRequest request) {
        log.info("createTournament api");

        try {
            tournamentService.createTournament(organizerId, request);
        } catch (CustomException exception) {
            return;
        }
    }

    @GetMapping("/{tournamentId}")
    public DetailTournamentResponse getDetailTournament(@PathVariable Long tournamentId) {
        log.info("getDetailTournament api");

        try {
            return tournamentService.getDetailTournament(tournamentId);
        } catch (CustomException exception) {
            return null;
        }
    }
}