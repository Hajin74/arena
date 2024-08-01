package org.example.boxingarena.ui;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.auth.CustomUserDetails;
import org.example.boxingarena.dto.tournament.TournamentCreateRequest;
import org.example.boxingarena.dto.tournament.TournamentDetailResponse;
import org.example.boxingarena.dto.tournament.TournamentSummaryResponse;
import org.example.boxingarena.dto.tournament.TournamentUpdateRequest;
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
    public List<TournamentDetailResponse> getTournamentsByOrganizer(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info("getTournamentsByOrganizer - api");

        try {
            return tournamentService.getTournamentsByOrganizer(customUserDetails);
        } catch (CustomException exception) {
            log.info("getTournamentsByOrganizer - exception : " + exception.getMessage());
            return null;
        }
    }

    @PatchMapping("/{tournamentId}/application-period/begin")
    public void beginApplicationPeriod(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long tournamentId) {
        log.info("beginApplicationPeriod - api : " + tournamentId);

        try {
            tournamentService.beginApplicationPeriod(customUserDetails, tournamentId);
        } catch (CustomException exception) {
            log.info("beginApplicationPeriod - exception : " + exception.getMessage());
        }
    }

    @PatchMapping("/{tournamentId}/application-period/end")
    public void endApplicationPeriod(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long tournamentId) {
        log.info("endApplicationPeriod - api : " + tournamentId);

        try {
            tournamentService.endApplicationPeriod(customUserDetails, tournamentId);
        } catch (CustomException exception) {
            log.info("endApplicationPeriod - exception : " + exception.getMessage());
        }
    }

    @PatchMapping("/{tournamentId}/begin")
    public void beginTournament(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long tournamentId) {
        log.info("beginTournament - api : " + tournamentId);

        try {
            tournamentService.beginTournament(customUserDetails, tournamentId);
        } catch (CustomException exception) {
            log.info("beginTournament - exception : " + exception.getMessage());
        }
    }

    @PatchMapping("/{tournamentId}/end")
    public void endTournament(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long tournamentId) {
        log.info("endTournament - api : " + tournamentId);

        try {
            tournamentService.endTournament(customUserDetails, tournamentId);
        } catch (CustomException exception) {
            log.info("endTournament - exception : " + exception.getMessage());
        }
    }

    @PatchMapping("/{tournamentId}")
    public void updateTournamentInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long tournamentId, @RequestBody @Valid TournamentUpdateRequest request) {
        log.info("updateTournamentInfo - api : " + tournamentId);

        try {
            tournamentService.updateTournamentInfo(customUserDetails, tournamentId, request);
        } catch (CustomException exception) {
            log.info("updateTournamentInfo - exception : " + exception.getMessage());
        }
    }

}
