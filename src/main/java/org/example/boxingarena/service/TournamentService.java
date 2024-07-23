package org.example.boxingarena.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.domain.Tournament;
import org.example.boxingarena.dto.DetailTournamentResponse;
import org.example.boxingarena.dto.TournamentCreateRequest;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.exception.ErrorCode;
import org.example.boxingarena.repository.OrganizerRepository;
import org.example.boxingarena.repository.TournamentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class TournamentService {

    private final OrganizerRepository organizerRepository;
    private final TournamentRepository tournamentRepository;

    @Transactional
    public void createTournament(Long organizerId, TournamentCreateRequest request) {
        log.info("createTournament service");

        if (!organizerRepository.existsById(organizerId)) {
            throw new CustomException(ErrorCode.ORGANIZER_NOT_FOUND);
        }

        Tournament newTournament = new Tournament(request.getName(), request.getLocation(),
                request.getStartDate(), request.getEndDate(), organizerId);
        tournamentRepository.save(newTournament);
    }

    @Transactional(readOnly = true)
    public DetailTournamentResponse getDetailTournament(Long tournamentId) {
        Tournament detailTournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND));

        return DetailTournamentResponse.from(detailTournament);
    }


}
