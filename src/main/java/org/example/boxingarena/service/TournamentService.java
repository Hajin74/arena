package org.example.boxingarena.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.domain.Tournament;
import org.example.boxingarena.repository.OrganizerRepository;
import org.example.boxingarena.repository.TournamentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class TournamentService {

    private OrganizerRepository organizerRepository;
    private TournamentRepository tournamentRepository;

    @Transactional
    public void createTournament(Long organizerId, String name, String location, LocalDate startDate, LocalDate endDate) {
        if (!organizerRepository.existsById(organizerId)) {
            // todo : 예외처리
            log.info("Invalid organizer ID: " + organizerId);
        }

        Tournament newTournament = new Tournament(name, location, startDate, endDate, organizerId);
        tournamentRepository.save(newTournament);
    }


}
