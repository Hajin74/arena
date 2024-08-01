package org.example.boxingarena.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.auth.CustomUserDetails;
import org.example.boxingarena.domain.Tournament;
import org.example.boxingarena.domain.User;
import org.example.boxingarena.dto.tournament.TournamentCreateRequest;
import org.example.boxingarena.dto.tournament.TournamentDetailResponse;
import org.example.boxingarena.dto.tournament.TournamentSummaryResponse;
import org.example.boxingarena.dto.tournament.TournamentUpdateRequest;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.exception.ErrorCode;
import org.example.boxingarena.repository.TournamentRepository;
import org.example.boxingarena.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TournamentService {

    private final UserRepository userRepository;
    private final TournamentRepository tournamentRepository;

    @Transactional
    public void createTournament(CustomUserDetails customUserDetails, TournamentCreateRequest request) {
        log.info("createTournament service");

        User organizer = userRepository.findByEmail(customUserDetails.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.ORGANIZER_NOT_FOUND));

        Tournament newTournament = Tournament.builder()
                .name(request.getName())
                .location(request.getLocation())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .posterImgUrl(request.getPosterImgUrl())
                .totalRoundsCount(request.getTotalRoundsCount())
                .intro(request.getIntro())
                .organizerId(organizer.getId())
                .build();

        log.info("newTournament : " + newTournament.toString());
        tournamentRepository.save(newTournament);
    }

    @Transactional(readOnly = true)
    public List<TournamentSummaryResponse> getAllTournament() {
        log.info("getAllTournament -  service");

        return tournamentRepository.findAll().stream()
                .map(TournamentSummaryResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TournamentDetailResponse getDetailTournament(Long tournamentId) {
        Tournament detailTournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND));

        User organizer = userRepository.findById(detailTournament.getOrganizerId())
                .orElseThrow(() -> new CustomException(ErrorCode.ORGANIZER_NOT_FOUND));

        return TournamentDetailResponse.from(detailTournament, organizer.getName());
    }

    @Transactional(readOnly = true)
    public List<TournamentDetailResponse> getTournamentsByOrganizer(CustomUserDetails customUserDetails) {
        log.info("getTournamentsByOrganizer - service");

        User organizer = userRepository.findByEmail(customUserDetails.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.ORGANIZER_NOT_FOUND));

        List<Tournament> tournaments =  tournamentRepository.findAllByOrganizerId(organizer.getId());
        List<TournamentDetailResponse> tournamentDetailResponses = new ArrayList<>();
        for (Tournament tournament : tournaments) {
            tournamentDetailResponses.add(TournamentDetailResponse.from(tournament, organizer.getName()));
        }

        return tournamentDetailResponses;
    }

    @Transactional
    public void beginApplicationPeriod(CustomUserDetails customUserDetails, Long tournamentId) {
        log.info("beginApplicationPeriod - service : " + tournamentId);

        User organizer = userRepository.findByEmail(customUserDetails.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.ORGANIZER_NOT_FOUND));

        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND));

        tournament.beginApplicationPeriod();
    }

    @Transactional
    public void endApplicationPeriod(CustomUserDetails customUserDetails, Long tournamentId) {
        log.info("endApplicationPeriod - service : " + tournamentId);

        User organizer = userRepository.findByEmail(customUserDetails.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.ORGANIZER_NOT_FOUND));

        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND));

        tournament.endApplicationPeriod();
    }

    @Transactional
    public void beginTournament(CustomUserDetails customUserDetails, Long tournamentId) {
        log.info("beginTournament - service : " + tournamentId);

        User organizer = userRepository.findByEmail(customUserDetails.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.ORGANIZER_NOT_FOUND));

        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND));

        tournament.beginTournament();
    }

    @Transactional
    public void endTournament(CustomUserDetails customUserDetails, Long tournamentId) {
        log.info("endTournament - service : " + tournamentId);

        User organizer = userRepository.findByEmail(customUserDetails.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.ORGANIZER_NOT_FOUND));

        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND));

        tournament.endTournament();
    }

    @Transactional
    public void updateTournamentInfo(CustomUserDetails customUserDetails, Long tournamentId, TournamentUpdateRequest request) {
        log.info("endTournament - service : " + tournamentId);

        User organizer = userRepository.findByEmail(customUserDetails.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.ORGANIZER_NOT_FOUND));

        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND));

        tournament.updateTournament(request);
    }

}
