package org.example.boxingarena.ui;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.auth.CustomUserDetails;
import org.example.boxingarena.dto.tournament.TournamentCreateRequest;
import org.example.boxingarena.dto.tournament.TournamentDetailResponse;
import org.example.boxingarena.dto.tournament.TournamentSummaryResponse;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.service.TournamentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/tournament")
@RequiredArgsConstructor
public class TournamentController {

    private final TournamentService tournamentService;

    @PostMapping
    public String createTournament(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody @Valid TournamentCreateRequest request) {
        log.info("createTournament - api");

        try {
            tournamentService.createTournament(customUserDetails, request);
            return "create success";
        } catch (CustomException exception) {
            return exception.getMessage();
        }
    }

    @GetMapping
    public List<TournamentSummaryResponse> getAllTournament() {
        log.info("getAllTournament - api");

        try {
            return tournamentService.getAllTournament();
        } catch (CustomException exception) {
            log.info("getAllTournament - exception : " + exception.getMessage());
            return null;
        }
    }

    @GetMapping("/{tournamentId}")
    public TournamentDetailResponse getDetailTournament(@PathVariable Long tournamentId) {
        log.info("getDetailTournament - api");

        try {
            return tournamentService.getDetailTournament(tournamentId);
        } catch (CustomException exception) {
            return null;
        }
    }

    @GetMapping("/organizer")
    public List<TournamentSummaryResponse> getTournamentsByOrganizer(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info("getTournamentsByOrganizer - api");

        try {
            return tournamentService.getTournamentsByOrganizer(customUserDetails);
        } catch (CustomException exception) {
            log.info("getTournamentsByOrganizer - exception : " + exception.getMessage());
            return null;
        }
    }

}
